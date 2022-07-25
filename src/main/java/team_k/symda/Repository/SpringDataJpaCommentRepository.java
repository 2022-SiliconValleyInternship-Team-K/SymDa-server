package team_k.symda.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team_k.symda.Constants.Emotion;
import team_k.symda.Constants.Weather;
import team_k.symda.Entity.Comment;

import java.util.Optional;

public interface SpringDataJpaCommentRepository extends JpaRepository<Comment, Long>, CommentRepository{
    @Override
    Optional<Comment> findByEmotionAndWeather(Emotion emotion, Weather weather);
}
