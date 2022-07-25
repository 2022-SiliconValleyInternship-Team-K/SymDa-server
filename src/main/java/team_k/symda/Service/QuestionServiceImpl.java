package team_k.symda.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team_k.symda.Entity.Question;
import team_k.symda.Repository.QuestionRepository;

import java.util.Optional;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService{

    // 의존성 주입 (DI)
    private final QuestionRepository questionRepository;
    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question findQuestion(Long question_id) {
        Optional<Question> questionById = questionRepository.findById(question_id);
        Question question = questionById.orElseThrow(); // question 객체가 null인 경우 에러를 throw 한다
        return question;
    }
}
