package team_k.symda.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team_k.symda.Entity.User;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public final class UserRequest {
    private User user_id;
    private String email;
    private String password;
}
