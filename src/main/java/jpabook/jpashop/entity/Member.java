package jpabook.jpashop.entity;


import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String password;
    private String username;
    private String email;


    @OneToMany(fetch = FetchType.LAZY,mappedBy = "member")
    private List<ThumbUp> thumbUps= new ArrayList<>();



}
