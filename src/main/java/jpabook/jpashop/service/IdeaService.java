package jpabook.jpashop.service;

import jpabook.jpashop.dto.IdeaFormDto;
import jpabook.jpashop.dto.IdeaListDto;
import jpabook.jpashop.dto.IdeaListSearch;
import jpabook.jpashop.dto.IdeaViewDto;
import jpabook.jpashop.entity.Category;
import jpabook.jpashop.entity.Idea;
import jpabook.jpashop.entity.Member;
import jpabook.jpashop.repository.CategoryRepository;
import jpabook.jpashop.repository.IdeaRepository;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IdeaService {

    //왜케 서비스단에서 하는게 없지???   exception 처리라도 해야지..

    private final IdeaRepository ideaRepository;
    private  final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;


    public Page<IdeaListDto> getIdeaList( Pageable pageable,IdeaListSearch ideaListSearchDto) {
        Page<IdeaListDto> pageResults = ideaRepository.ideaSearchPage(pageable, ideaListSearchDto);
        return pageResults;
    }



    public IdeaViewDto getIdea(Long id){
        return ideaRepository.getIdeaView(id);
    }

    //save 한번에 select 2번은 괜찮나??    이부분을 많이 고민했는데  실제 category나 member의 메소드 실행안하고 단순히 프록시객체만 가지고 진행하면 쿼리 안날라감.
    public Long insertIdea(IdeaFormDto ideaFormDto){

        Member member= memberRepository.getOne(ideaFormDto.getMemberId());
        Category category= categoryRepository.getCategoryByCategoryCd(ideaFormDto.getCategoryCd());
        Idea idea = Idea.createIdea(member, category, ideaFormDto.getTitle(), ideaFormDto.getContent());
        return ideaRepository.save(idea).getId();   //Entity를 그대로 controller주면 안되고, Id만 주도록.
    }





}
