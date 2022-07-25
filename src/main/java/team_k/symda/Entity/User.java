package team_k.symda.Entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;   // 유저 pk

    private String email;   // 이메일 주소

    private String password;    // 비밀번호

    @Builder
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
