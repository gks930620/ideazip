package jpabook.jpashop.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
//네이버나 카카오 API를 쓰면 어떻게 바뀌어야 할까
public class Member {


    public Member(String password, String username, String email, String hp, String birthday, String zipcode, String add, String addDetail) {
        this.password = password;
        this.username = username;
        this.email = email;
        this.hp = hp;
        this.birthday = birthday;
        this.zipcode = zipcode;
        this.add = add;
        this.addDetail = addDetail;
    }

    @Id@GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String password;
    private String username;
    private String email;

    private String hp; //전화번호
    private String birthday; //생일
    private String zipcode; //우편번호
    private String add; //주소
    private String addDetail; //상세주소
    private String memberDelYn="N";



    @OneToMany(fetch = FetchType.LAZY,mappedBy = "member")
    private List<ThumbUp> thumbUps= new ArrayList<>();



}
