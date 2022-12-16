package jpabook.jpashop.repository;

import jpabook.jpashop.dto.LoginDto;

public interface MemberRepositoryCustom {

    LoginDto findLoginDto(String id);
}
