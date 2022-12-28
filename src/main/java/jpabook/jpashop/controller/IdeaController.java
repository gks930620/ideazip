package jpabook.jpashop.controller;

import com.google.gson.JsonObject;
import jpabook.jpashop.dto.*;
import jpabook.jpashop.service.CategoryService;
import jpabook.jpashop.service.FileService;
import jpabook.jpashop.service.IdeaService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.file.Files;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class IdeaController {
    private final IdeaService ideaService;
    private  final CategoryService categoryService;
    private final FileService fileService;

    @ModelAttribute("categoryList")
    public List<CategoryDto> categoryList(){
        return categoryService.categoryDtoList();
    }

    @RequestMapping("/idea/ideaList")
    public String ideaList(Model model, @PageableDefault(size = 10)Pageable pageable
            , @ModelAttribute("search") IdeaListSearch ideaListSearchDto){  // 검색과 페이징은 따로따로.  페이징 : 파라미터이름이 page,size,sort
        Page<IdeaListDto> pageResults = ideaService.getIdeaList(pageable, ideaListSearchDto);
        List<IdeaListDto> ideaList = pageResults.getContent();

        model.addAttribute("page",pageResults);
        model.addAttribute("ideaList",ideaList);
        return "idea/ideaList";
    }


    @GetMapping("/idea/ideaView")
    public String ideaView(Model model, Long id){
        IdeaViewDto idea= ideaService.getIdea(id);
        model.addAttribute("idea",idea);
        return "idea/ideaView";
    }

    @GetMapping("/idea/ideaForm")  //interceptor나 session없이 security를 통해서.
    public String ideaForm(){
        return "idea/ideaForm";
    }

    @PostMapping("/idea/ideaInsert")   //ckEditor로 이용한 이미지 업로드는 따로 파일업로드 경로가 따로 있지만, 단순 파일첨부 처리는 여기서 한다. 어차피 서비스로 하니까 뭐 코드 중복 될 일은 없지.
    public String ideaInsert(
            IdeaFormDto ideaFormDto,
            @RequestParam(value = "boFiles",required = false) MultipartFile[] boFiles
            , Principal principal // security에서 제공하는 로그인정보(현재 로그인된 id,pw 등)
    ) throws IOException {
        //set말고 그냥 attach Service  , ideaService 따로하면 될 거 같은데.. 굳이 set해야되나?
        if(boFiles!=null){
            //파일처리는 나중에
        }
        ideaFormDto.setMemberId(principal.getName());  //   getName이지만 id임   security에서는 name(id), pw만 있음.  username이 필요하면 id로 DB조회하자.
        Long ideaId=ideaService.insertIdea(ideaFormDto);

        return "redirect:/idea/ideaView?id="+ideaId;
    }


    //insert에서 파일처리하는거(ckeditor말고 따로 올린 파일),  fileUpload에서 DB에도 업로드하기,   edit하기     여기까지 하고 정리하기 . 내일 다 할 수 있다 아자아자



    //오르지 ckeditor만을위한거
    //json형태로 return하라고 ckeditor document에 있음.
    @PostMapping("/idea/fileUpload")
    @ResponseBody
    public String fileUpload(HttpServletRequest request, HttpServletResponse response,
                             MultipartHttpServletRequest multiFile) throws IOException {
        //Json 객체 생성
        JsonObject json = new JsonObject();
        // Json 객체를 출력하기 위해 PrintWriter 생성
        PrintWriter printWriter = null;
        OutputStream out = null;
        //파일을 가져오기 위해 MultipartHttpServletRequest 의 getFile 메서드 사용
        MultipartFile multipart = multiFile.getFile("upload");  //ckeditor측에서 이름을 upload로 해놓음
        //파일이 비어있지 않고(비어 있다면 null 반환)
        if (multipart != null) {
            // 파일 사이즈가 0보다 크고, 파일이름이 공백이 아닐때
            if (multipart.getSize() > 0 && StringUtils.isNotBlank(multipart.getName())) {
                if (multipart.getContentType().toLowerCase().startsWith("image/")) {
                    try {

                        //여기서 실제로 파일이 저장 + AttachDto return
                        AttachDto attachDto = fileService.getAttachByMultipart(multipart, "IDEA", "idea");

                        //파일 정보를 DB에 저장하는건 나중에 하자..

                        //ckeditor쪽에 보낼 데이터 설정
                        //파일 이름 설정
                        String fileName = attachDto.getAttachFileName();  //multipart.getName()
                        //클라이언트에 이벤트 추가   왜 text/html타입이지??
                        printWriter = response.getWriter();
                        response.setContentType("text/html");

                        //파일이 연결되는 Url 주소 설정
                        String fileUrl = "/attach/"+fileName;   //파일을 찾는데는 attachNo로 찾을 수도 있겠지만, 그럼 사용자가 맘대로 다른것도 찾을 수도 있으니까...

                        //생성된 jason 객체를 이용해 파일 업로드 + 이름 + 주소를 CkEditor에 전송
                        json.addProperty("uploaded", 1);
                        json.addProperty("fileName", fileName);
                        json.addProperty("url", fileUrl);
                        printWriter.println(json);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if(out !=null) {
                            out.close();
                        }
                        if(printWriter != null) {
                            printWriter.close();
                        }
                    }
                }
            }
        }
        return null;
    }


    //1. <script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction('콜백의 식별 ID 값', '파일의 URL', '전송완료 메시지')</script>
    //2. url에 맞는 controller로 파일이나 byte형태 주면 ckeditor가 알아서  미리보기까지는 해줄거같은데...
    //3. 이건 신경쓰지말자...  미리보기로 전송된 파일이 실제로 저장 안됐을 때는 괜히 이미지 많이 저장되는거 아니냐... 그렇게 많지는 않을텐데...
    //4. 일단 DB에도 저장되야 한다.
    // 내 선택은 2번인데.. 1번의 경우 서버측, 즉 프로젝트에 파일이 업로드 됐을때의 사용하는경우..  그러므로 2번을 선택하자.
    // 2번이 맞았다!!!!
    //img파일 썸네일
    //오르지 ckeditor만을위한거
    @GetMapping("/attach/{fileName}")
    @ResponseBody
    public ResponseEntity<byte[]> getFile(@PathVariable("fileName") String fileName){
        String filePath=fileService.findFileByPathAndName("idea",fileName);   //ckeditor에서만 쓰는거라 idea그냥 문자열로 썻는데,, 나중에 img 업로드 다른고셍서 하는곳 또 있으면 이 메소드 하나로 처리하고 싶을텐데.. idea어떻게 파라미터로 받을까..
        File file=new File(filePath);
        ResponseEntity<byte[]> result=null;
        try {
            HttpHeaders headers=new HttpHeaders();
            headers.add("Content-Type", Files.probeContentType(file.toPath()));
            result=new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),headers, HttpStatus.OK );
        }catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    //나중에 수정할 때 ckeditor에서 올렸던 내용 그대로 보여져야 할텐데... view랑 edit이 다르네...
    // 저장할 때   파일정보가 오냐...

}
