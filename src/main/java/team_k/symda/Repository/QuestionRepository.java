package team_k.symda.Repository;

import team_k.symda.Entity.Question;

import java.util.Optional;

public interface QuestionRepository {
    /*
    * id로 찾기
    * */
    Optional<Question> findById(Long id);
}
