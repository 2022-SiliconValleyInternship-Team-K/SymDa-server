package team_k.symda.Repository;

import team_k.symda.Constants.Emotion;
import team_k.symda.Constants.Weather;
import team_k.symda.Entity.Comment;

import java.util.Optional;

public interface CommentRepository {
    /*
    * emotion & weather 로 찾기
    * */
    Optional<Comment> findByEmotionAndWeather(Emotion emotion, Weather weather);
}
