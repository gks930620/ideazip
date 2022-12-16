package jpabook.jpashop.repository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jpabook.jpashop.dto.LoginDto;
import jpabook.jpashop.dto.QLoginDto;
import jpabook.jpashop.entity.QMember;
import org.springframework.beans.factory.annotation.Autowired;

import static jpabook.jpashop.entity.QMember.member;


public class MemberRepositoryImpl  implements  MemberRepositoryCustom{

    @Autowired
    JPAQueryFactory queryFactory;

    @Override
    public LoginDto findLoginDto(String id) {
        return queryFactory.select(new QLoginDto(member.id,member.password,member.role))
                .from(member)
                .where(member.id.eq(id))
                .fetchOne();

    }
}
