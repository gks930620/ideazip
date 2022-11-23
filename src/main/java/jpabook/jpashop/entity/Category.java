package jpabook.jpashop.entity;


import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;
    private String categoryCd;  //카테고리 코드 JB00
    private String categoryName; //   카테고리 이름 BC00의 실제이름은 직업분류 라던가. JB01은  "교사" 등

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Idea> ideas= new ArrayList<>();


}
