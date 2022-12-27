package jpabook.jpashop.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IdeaFormDto {

    private String categoryCd;
    private String title;
    private String content;
    private String memberId;


}
