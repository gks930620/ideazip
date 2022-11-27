package jpabook.jpashop.ideadto;

import jpabook.jpashop.entity.BaseTimeEntity;
import jpabook.jpashop.entity.Category;
import jpabook.jpashop.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class IdeaListDto   {
    public IdeaListDto(Long id, String categoryName, String username, String title,LocalDateTime createdDate) {
        this.id = id;
        this.categoryName = categoryName;
        this.username = username;
        this.title = title;
        this.createdDate=createdDate;
    }

    private Long id;
    private String categoryName;
    private String username;
    private String title;
    private LocalDateTime createdDate;


}
