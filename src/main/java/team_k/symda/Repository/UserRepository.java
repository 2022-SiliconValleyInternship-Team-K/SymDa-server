package team_k.symda.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team_k.symda.Entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /*
    * Email로 찾기
    * */
    User findByEmail(String email);

    /*
     * id로 찾기
     * */
    Optional<User> findById(Long id);
}

