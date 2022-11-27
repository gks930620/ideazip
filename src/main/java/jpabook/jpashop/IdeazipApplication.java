package jpabook.jpashop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //auditing기능 활성화.  BaseTimeEntity 참고
@SpringBootApplication
public class IdeazipApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdeazipApplication.class, args);
	}

}
