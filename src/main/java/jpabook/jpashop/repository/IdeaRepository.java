package jpabook.jpashop.repository;

import jpabook.jpashop.entity.Idea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface IdeaRepository  extends JpaRepository<Idea,Long> {



}



