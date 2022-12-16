package jpabook.jpashop.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class LoginDto {
    @QueryProjection
    public LoginDto(String id, String password, String role) {
        this.id = id;
        this.password = password;
        this.role=role;
    }

    private String id;
    private String password;
    private String role;
}
