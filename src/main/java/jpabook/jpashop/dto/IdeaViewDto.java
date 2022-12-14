package jpabook.jpashop.dto;

import com.querydsl.core.annotations.QueryProjection;
import jpabook.jpashop.entity.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class IdeaViewDto {


    @QueryProjection
    public IdeaViewDto(Long id, String categoryName, String username, String title, String content, LocalDateTime createdDate, LocalDateTime lastModifiedDate
    ) {
        this.id = id;
        this.categoryName = categoryName;
        this.username = username;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }




    private Long id;   //글번호입니당 ㅎ
    private String categoryName;
    private String username;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    private List<AttachDto> attaches= new ArrayList<>();

    //private List<ThumbUp> thumbUps= new ArrayList<>();
    //private List<UpdateHit> updateHits=new ArrayList<>();







}
