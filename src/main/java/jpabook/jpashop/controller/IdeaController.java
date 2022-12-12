package jpabook.jpashop.controller;

import jpabook.jpashop.entity.Idea;
import jpabook.jpashop.ideadto.IdeaListDto;
import jpabook.jpashop.ideadto.IdeaListSearch;
import jpabook.jpashop.service.IdeaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class IdeaController {
    private final IdeaService ideaService;

    @GetMapping("/idea/ideaList")
    public String ideaList(Model model, @PageableDefault(size = 10)Pageable pageable, IdeaListSearch ideaListSearchDto){  // 검색과 페이징은 따로따로.  페이징 : 파라미터이름이 page,size,sort
        System.out.println("controller단 파라미터= " + ideaListSearchDto);
        Page<IdeaListDto> pageResults = ideaService.getIdeaList(pageable, ideaListSearchDto);
        List<IdeaListDto> ideaList = pageResults.getContent();



        model.addAttribute("page",pageResults);
        model.addAttribute("ideaList",ideaList);
        return "idea/ideaList";
    }
}
