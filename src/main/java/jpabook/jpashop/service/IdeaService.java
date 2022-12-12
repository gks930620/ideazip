package jpabook.jpashop.service;

import jpabook.jpashop.entity.Idea;
import jpabook.jpashop.ideadto.IdeaListDto;
import jpabook.jpashop.ideadto.IdeaListSearch;
import jpabook.jpashop.repository.IdeaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IdeaService {

    private final IdeaRepository ideaRepository;

    public Page<IdeaListDto> getIdeaList( Pageable pageable,IdeaListSearch ideaListSearchDto) {
        Page<IdeaListDto> pageResults = ideaRepository.ideaSearchPage(pageable, ideaListSearchDto);
        return pageResults;
    }

}
