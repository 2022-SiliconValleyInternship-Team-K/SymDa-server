package team_k.symda.Service;

import team_k.symda.Entity.Question;

public interface QuestionService {
    /*
     * 오늘의 질문 조회
     * */
    public Question findQuestion(Long question_id);
}
