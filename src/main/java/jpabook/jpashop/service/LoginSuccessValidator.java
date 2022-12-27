package jpabook.jpashop.service;


import jpabook.jpashop.dto.LoginDto;
import jpabook.jpashop.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


/**
 *  로그인 성공여부를 판단하는 service,  나중에 api 로그인 썻을 때 수정해야 할 듯
 */
@Service
public class LoginSuccessValidator  implements UserDetailsService {

    @Autowired
    MemberRepository memberRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    //비밀번호 인코딩.  security는 이게 안 되어있으면 알아서 에러일으켜서 꼭 해줘야함.
    // 그럼 무조건 insert하는 부분에서도 encoding 되서 넣어야 함.


    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        LoginDto user = memberRepository.findLoginDto(id);  //DB조회해서 얻은게 LoginDto인데..
        if (user == null) {
            return null;
        }
        String pw = user.getPassword();
        String role =user.getRole();
        return User.builder()
                .username(id)
                .password(pw)
                .roles(role)
                .build();
    }
}
