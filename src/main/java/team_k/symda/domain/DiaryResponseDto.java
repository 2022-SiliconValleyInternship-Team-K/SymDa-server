package team_k.symda.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import team_k.symda.Constants.Emotion;
import team_k.symda.Constants.Weather;
import team_k.symda.Entity.Comment;
import team_k.symda.Entity.Question;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class DiaryResponseDto {
    // id
    private Long diary_id;

    // content
    private String content;

    // weather
    private Weather weather;

    // createdAt
    private LocalDate createdAt;

    // month
    private String month;

    //date
    private String date;

    // emotion
    private Emotion emotion;

    // question
    private Question question;

    // comment
    private Comment comment;

    // imageUrl
    private String imageUrl;

    // -> user 만 빼고 리턴

    @Builder
    public DiaryResponseDto(Long diary_id, String content, Weather weather, LocalDate createdAt, String month, String date, Emotion emotion, Question question, Comment comment, String imageUrl) {
        this.diary_id = diary_id;
        this.content = content;
        this.weather = weather;
        this.createdAt = createdAt;
        this.month = month;
        this.date = date;
        this.emotion = emotion;
        this.question = question;
        this.comment = comment;
        this.imageUrl = imageUrl;
    }
}
