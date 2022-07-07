package team_k.symda.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import team_k.symda.Constants.Emotion;
import team_k.symda.Constants.Weather;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter @ToString
@Entity
public class Diary {

    // 일기 pk
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diary_id;

    // 일기 내용
    @Column(length = 1000)
    private String content;

    // 날씨
    @Enumerated(EnumType.STRING)
    private Weather weather;

    // 생성 시간
    private LocalDateTime created_at;
    @PrePersist
    public void created_at(){
        this.created_at = LocalDateTime.now();
        setMonth(created_at);
    }

    // 생성 연월
    private String month;
    public void setMonth(LocalDateTime created_at) {
        String year = Integer.toString(created_at.getYear());
        String month = Integer.toString(created_at.getMonthValue());
        this.month = year+month;
    }

    // 감정
    @Enumerated(EnumType.STRING)
    private Emotion emotion;

    // 유저 pk (FK)
    @ManyToOne  // 다대일 단방향 관계
    @JoinColumn(name = "user_id")
    private User user;

    // 질문 pk (FK)
    @OneToOne   // 일대일 단방향 관계
    @JoinColumn(name = "question_id")
    private Question question;


    public Diary(Long diary_id, String content, Weather weather, LocalDateTime created_at, String month, Emotion emotion, User user, Question question) {
        this.diary_id = diary_id;
        this.content = content;
        this.weather = weather;
        this.created_at = created_at;
        this.month = month;
        this.emotion = emotion;
        this.user = user;
        this.question = question;
    }
}
