package jpabook.jpashop.repository;

import jpabook.jpashop.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository  extends JpaRepository<Member,String>, MemberRepositoryCustom {


}
