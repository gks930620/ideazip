package jpabook.jpashop.repository;

import jpabook.jpashop.ideadto.IdeaListDto;
import jpabook.jpashop.ideadto.IdeaListSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IdeaRepositoryCustom  {

    public Page<IdeaListDto> ideaSearchPage(Pageable pageable ,IdeaListSearch ideaListSearch); //실제로 화면에 보여줄 DTO

}
