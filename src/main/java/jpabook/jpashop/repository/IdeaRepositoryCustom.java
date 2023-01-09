package jpabook.jpashop.repository;

import jpabook.jpashop.dto.IdeaEditDto;
import jpabook.jpashop.dto.IdeaListDto;
import jpabook.jpashop.dto.IdeaListSearch;
import jpabook.jpashop.dto.IdeaViewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IdeaRepositoryCustom  {

    public Page<IdeaListDto> ideaSearchPage(Pageable pageable ,IdeaListSearch ideaListSearch); //실제로 화면에 보여줄 DTO
    public IdeaViewDto getIdeaView(Long id);
    public IdeaEditDto getIdeaEdit(Long id);

}
