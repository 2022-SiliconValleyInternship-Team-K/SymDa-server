package team_k.symda.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import team_k.symda.Entity.User;
import team_k.symda.Repository.UserRepository;
import team_k.symda.SymdaApplication;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = SymdaApplication.class)
public class UserControllerTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void signup() {
        //given
        User user = new User();
        user.setEmail("user@email.com");
        user.setPassword("dfajladsjfl*1");

        //when
        User joinUser = userRepository.save(user);
        User findUser = userRepository.findByEmail(joinUser.getEmail());

        //then
        assertThat(findUser.getEmail()).isEqualTo(user.getEmail());
        //assertThat(findUser).isEqualTo(joinUser);
    }
}