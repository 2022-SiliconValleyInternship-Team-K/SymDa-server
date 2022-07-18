package team_k.symda.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team_k.symda.Entity.User;
import team_k.symda.Repository.UserRepository;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public String signup(UserRequest request){
        userRepository.save(User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .build());
        return "Success";
    }

    public String login(String userId, String password) {
        User user = userRepository.findByEmail(userId);
        log.info("db password = {}, input password = {}", user.getPassword(), password);
        if(user.getPassword().equals(password)) {
            return "Success";
        }
        return "Failed";
    }
}
