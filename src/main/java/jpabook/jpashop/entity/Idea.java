package jpabook.jpashop.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
public class Idea extends  BaseTimeEntity{

    @Id@GeneratedValue
    @Column(name = "idea_id" )  //pk에는 updatable false 해야되는거 아닌가?
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")   //연관관계주인은 요기.    Category의 maapedBy는  Idea테이블의 컬럼이름이 아닌 엔티티의 필드이름. 즉 category
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "content")
    private String content;

    @Column(name = "del_yn")
    private String ideaDelYn;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "idea")
    private List<AttachIdea> attaches= new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "idea")
    private List<ThumbUp> thumbUps= new ArrayList<>();





}
