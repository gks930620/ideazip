package jpabook.jpashop.service;

import jpabook.jpashop.entity.*;
import jpabook.jpashop.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InitService {
    private final IdeaRepository ideaRepository;
    private final MemberRepository memberRepository;
    private final ThumbUpRepository thumbUpRepository;
    private  final CategoryRepository categoryRepository;
    private final AttachRepository attachRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void init() {
        Category category=new Category("BI01","공공기관"); //    일단은 기발한아이디어(BI)로    공공기관, 기발제품
        Category category2=new Category("BI02","기발제품");
        categoryRepository.save(category);
        categoryRepository.save(category2);

        List<Idea> ideas = new ArrayList<>();
        Member member1 = new Member("id1",passwordEncoder.encode("password") , "USER","username1", "email", "hp", "birthday", "zipcode", "add", "addDetail");
        memberRepository.save(member1);
        for (int i = 0; i < 100; i++) {
            Idea idea = new Idea("제목" + i, "내용"+i);
            idea.setMember(member1);   //나중에 set말고 연관관계 메소드 잘 만들어야겠지만 일단은 여기까지.
            idea.setCategory(category);
            ideaRepository.save(idea);  //set을 먼저하면 member_id가 한번에 insert
            ideas.add(idea);
        }
        Member member2 = new Member("id2",passwordEncoder.encode("password") ,"USER", "username2", "email", "hp", "birthday", "zipcode", "add", "addDetail");
        memberRepository.save(member2);
        for (int i = 100; i < 215 ; i++) {
            Idea idea = new Idea("제목" + i, "내용"+i);
            ideaRepository.save(idea); //일단 insert (물론 실제쿼리는 나중에 실행, 영속성컨테스트에 sql쿼리 날리는거임)
            idea.setMember(member2);  //save 후 set하면 나중에 변경감지에서 update  문까지  영속성 컨테스트에...     나중에 trasaction 후 모든 쿼리 쏴악  변경감지도 어쨋든 update쿼리실행이니 최대한 적은게 좋음
            idea.setCategory(category2);
            ideas.add(idea);
        }
        for (int i = 3; i <= 5; i++) { //member 3,4,5는 글 안쓴 사람
            Member member = new Member("id"+i,passwordEncoder.encode("password") , "USER","username" + i, "email", "hp", "birthday", "zipcode", "add", "addDetail");
            memberRepository.save(member);
            for (int j = 0; j < ideas.size(); j = j + i) {
                ThumbUp thumbUp = new ThumbUp();
                thumbUp.setIdea(ideas.get(j));
                thumbUp.setMember(member);
                thumbUpRepository.save(thumbUp);
            }
        }
        Optional<Idea> one = ideaRepository.findById(217L);
        Idea idea1 = one.get();
        Attach attach = new Attach(idea1, "IDEA", "1e68bec7-39ac-43de-b8e6-125807eb17a3"
                , "화면 캡처 2022-12-23 145025.png", 34183L, "33.4 KB", "image/png", "idea");

        Attach attach2 = new Attach(idea1, "IDEA", "84fcfc05-8e6a-4647-8c13-7357b817623c"
                , "화면 캡처 2022-12-23 145025.png", 34183L, "33.4 KB", "image/png", "idea");

        attachRepository.save(attach);
        attachRepository.save(attach2);
    }

}
