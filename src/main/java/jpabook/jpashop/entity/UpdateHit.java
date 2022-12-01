package jpabook.jpashop.entity;


import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class UpdateHit {   //조회수

    @Id
    @GeneratedValue
    private Long id;

    private String ip;
    private LocalDateTime createdDate;  //첫 조회

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idea_id")
    private Idea idea;






}
