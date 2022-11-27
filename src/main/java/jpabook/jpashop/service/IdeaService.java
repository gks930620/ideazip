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

    public List<IdeaListDto> getIdeaList(IdeaListSearch ideaListSearchDto, Pageable pageable) {
        Page<Idea> page = ideaRepository.findAll(pageable);
        Page<IdeaListDto> dtoMap = page.map(idea ->
                new IdeaListDto(idea.getId(), idea.getCategory().getCategoryName(), idea.getMember().getUsername(), idea.getTitle(),idea.getCreatedDate()));

        return dtoMap.getContent();

    }

}
