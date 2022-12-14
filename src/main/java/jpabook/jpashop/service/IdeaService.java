package jpabook.jpashop.service;

import jpabook.jpashop.dto.IdeaListDto;
import jpabook.jpashop.dto.IdeaListSearch;
import jpabook.jpashop.dto.IdeaViewDto;
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





}
