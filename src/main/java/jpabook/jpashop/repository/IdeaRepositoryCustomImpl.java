package jpabook.jpashop.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
//import jpabook.jpashop.entity.QIdea;
import jpabook.jpashop.entity.QCategory;
import jpabook.jpashop.entity.QMember;
import jpabook.jpashop.ideadto.IdeaListDto;
import jpabook.jpashop.ideadto.IdeaListSearch;
import jpabook.jpashop.ideadto.QIdeaListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static jpabook.jpashop.entity.QCategory.category;
import static jpabook.jpashop.entity.QIdea.idea;
import static jpabook.jpashop.entity.QMember.member;
import static org.springframework.util.StringUtils.hasText;

public class IdeaRepositoryCustomImpl implements IdeaRepositoryCustom {


    private BooleanExpression searchWord(String searchType,String searchWord) { // 검색타입 검색어와  항상 같이
        System.out.println("searchType = " + searchType);
        System.out.println("searchWord = " + searchWord);
        if (hasText(searchType)) {
            switch (searchType) {
                case "T":  //제목
                    return idea.title.contains(searchWord);
                case "W":   // writer 작성자
                    return idea.member.username.contains(searchWord);
                case "C":  //content
                    return idea.content.contains(searchWord);
            }
        }
        return null;
    }

    private BooleanExpression searchCategory(String searchCategory) {
        return hasText(searchCategory) ? idea.category.categoryCd.eq(searchCategory) : null;
    }


    @Autowired
    private JPAQueryFactory queryFactory;   //난 빈등록했으니까..


    @Override
    public Page<IdeaListDto> ideaSearchPage(Pageable pageable, IdeaListSearch ideaListSearch) {
        QueryResults<IdeaListDto> result = queryFactory.select(new QIdeaListDto(idea.id, idea.category.categoryName, idea.member.username, idea.title, idea.createdDate))
                .from(idea)
                .leftJoin(idea.category, category)
                .leftJoin(idea.member, member)
                .where(  searchWord(ideaListSearch.getSearchType(),ideaListSearch.getSearchWord()), searchCategory(ideaListSearch.getSearchCategory())  )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())    //offset : 어디서부터 가져올지,  limit : 얼마나 가져올지,    pageSize : 한 페이지의 글 개수
                .orderBy(idea.id.desc())
                .fetchResults();

        List<IdeaListDto> content = result.getResults();
        long total = result.getTotal();
        return new PageImpl<>(content, pageable, total);
    }
}
