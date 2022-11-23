package jpabook.jpashop.repository;

import jpabook.jpashop.entity.Idea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdeaRepository  extends JpaRepository<Idea,Long> {

}
