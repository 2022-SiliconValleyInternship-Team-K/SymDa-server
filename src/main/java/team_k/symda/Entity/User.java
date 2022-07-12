package team_k.symda.Entity;

import lombok.*;

import javax.persistence.*;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
@Entity
@Builder
@Table(name="User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column
    private String email;

    @Column
    private String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
