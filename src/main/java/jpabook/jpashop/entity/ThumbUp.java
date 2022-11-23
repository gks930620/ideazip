package jpabook.jpashop.entity;


import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@Entity
public class ThumbUp {

    @Id
    @GeneratedValue
    @Column(name = "thumbUp_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idea_id")
    private Idea idea;

}
