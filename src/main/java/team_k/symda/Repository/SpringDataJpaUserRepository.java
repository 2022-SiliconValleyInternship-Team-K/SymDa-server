package team_k.symda.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import team_k.symda.Entity.User;

public interface SpringDataJpaUserRepository extends JpaRepository<User, Long>, UserRepository{
    @Override
    User findByEmail(String email);
}
