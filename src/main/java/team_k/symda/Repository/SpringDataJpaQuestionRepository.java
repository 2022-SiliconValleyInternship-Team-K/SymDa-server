package team_k.symda.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team_k.symda.Entity.Question;

public interface SpringDataJpaQuestionRepository extends JpaRepository<Question, Long>, QuestionRepository{

}
