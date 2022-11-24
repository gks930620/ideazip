package jpabook.jpashop.service;

import jpabook.jpashop.entity.Idea;
import jpabook.jpashop.entity.Member;
import jpabook.jpashop.entity.ThumbUp;
import jpabook.jpashop.repository.IdeaRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.ThumbUpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InitService {
    private final IdeaRepository ideaRepository;
    private final MemberRepository memberRepository;
    private final ThumbUpRepository thumbUpRepository;

    @Transactional
    public void init() {
        List<Idea> ideas = new ArrayList<>();

        Member member1 = new Member("password", "username1", "email", "hp", "birthday", "zipcode", "add", "addDetail");
        memberRepository.save(member1);
        for (int i = 0; i < 10; i++) {
            Idea idea = new Idea("내용" + i);
            idea.setMember(member1);   //나중에 set말고 연관관계 메소드 잘 만들어야겠지만 일단은 여기까지.
            ideaRepository.save(idea);  //set을 먼저하면 member_id가 한번에 insert
            ideas.add(idea);
        }
        Member member2 = new Member("password", "username2", "email", "hp", "birthday", "zipcode", "add", "addDetail");
        memberRepository.save(member2);
        for (int i = 10; i < 20; i++) {
            Idea idea = new Idea("내용" + i);
            ideaRepository.save(idea); //일단 insert (물론 실제쿼리는 나중에 실행, 영속성컨테스트에 sql쿼리 날리는거임)
            idea.setMember(member2);  //save 후 set하면 나중에 변경감지에서 update  문까지  영속성 컨테스트에...     나중에 trasaction 후 모든 쿼리 쏴악  변경감지도 어쨋든 update쿼리실행이니 최대한 적은게 좋음
            ideas.add(idea);
        }
        for (int i = 3; i <= 5; i++) { //member 3,4,5는 글 안쓴 사람
            Member member = new Member("password", "username" + i, "email", "hp", "birthday", "zipcode", "add", "addDetail");
            memberRepository.save(member);
            for (int j = 0; j < ideas.size(); j = j + i) {
                ThumbUp thumbUp = new ThumbUp();
                thumbUp.setIdea(ideas.get(j));
                thumbUp.setMember(member);
                thumbUpRepository.save(thumbUp);
            }
        }

    }

}
