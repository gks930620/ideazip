package jpabook.jpashop.ideadto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class IdeaListSearch {

    private String searchType;   //작성자(member)인지, 제목인지
    private String searchWord;
    private String categoryCd;  //나중에 select 태그에서 선택할수있게

//    private MemberDto member;
//    private String title;
//    private LocalDateTime createdDate;    //나중에 혹시 검색기간조회기능도만들거면 ..


}
