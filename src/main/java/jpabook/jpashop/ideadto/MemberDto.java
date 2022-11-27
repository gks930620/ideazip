package jpabook.jpashop.ideadto;


import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class MemberDto{
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


}
