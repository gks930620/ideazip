package jpabook.jpashop.controller;

import jpabook.jpashop.dto.CategoryDto;
import jpabook.jpashop.dto.IdeaListDto;
import jpabook.jpashop.dto.IdeaListSearch;
import jpabook.jpashop.dto.IdeaViewDto;
import jpabook.jpashop.service.CategoryService;
import jpabook.jpashop.service.IdeaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class IdeaController {
    private final IdeaService ideaService;
    private  final CategoryService categoryService;

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

    @GetMapping("/idea/ideaForm")  //interceptor나 session없이 security를 통해서
    public String ideaForm(){
        return "idea/ideaForm";
    }


}
