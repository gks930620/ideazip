package jpabook.jpashop.service;

import jpabook.jpashop.dto.IdeaFormDto;
import jpabook.jpashop.dto.IdeaListDto;
import jpabook.jpashop.dto.IdeaListSearch;
import jpabook.jpashop.dto.IdeaViewDto;
import jpabook.jpashop.entity.Idea;
import jpabook.jpashop.repository.IdeaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IdeaService {

    //왜케 서비스단에서 하는게 없지???   exception 처리라도 해야지..

    private final IdeaRepository ideaRepository;



    public Page<IdeaListDto> getIdeaList( Pageable pageable,IdeaListSearch ideaListSearchDto) {
        Page<IdeaListDto> pageResults = ideaRepository.ideaSearchPage(pageable, ideaListSearchDto);
        return pageResults;
    }



    public IdeaViewDto getIdea(Long id){
        return ideaRepository.getIdeaView(id);
    }

    public int insertIdea(IdeaFormDto ideaFormDto){
        //여기다 저장할때 난 memberId, categoryId만 갖고있는데 이거 entity를저장할 때 DB로 select 하면 좀 그렇지??
        //  프러ㅗㄱ시를 이용하라는데 즐겨찾기 보고 해결해보자

        return 0;


    }





}
