package team_k.symda.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import team_k.symda.Constants.Emotion;
import team_k.symda.Constants.Weather;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diary_id;  // 일기 pk

    @Column(length = 1000)
    private String content; // 일기 내용

    @Enumerated(EnumType.STRING)
    private Weather weather;    // 날씨

    private LocalDateTime created_at;   // 생성 시간
    @PrePersist // DB에 해당 테이블의 insert 연산을 실행할 때 같이 실행해라
    public void created_at(){
        this.created_at = LocalDateTime.now();
        setMonth(created_at);
    }

    private String month;   // 연월
    public void setMonth(LocalDateTime created_at) {
        String year = Integer.toString(created_at.getYear());
        String month = Integer.toString(created_at.getMonthValue());
        this.month = year+month;
    }

    @Enumerated(EnumType.STRING)
    private Emotion emotion;    // 감정

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;  // 유저 pk (FK)

    @OneToOne   // 일대일 단방향 관계
    @JoinColumn(name = "question_id")
    private Question question;  // 질문 pk (FK)

    public Diary(String content, Weather weather, LocalDateTime created_at, String month, Emotion emotion, User user, Question question) {
        this.content = content;
        this.weather = weather;
        this.created_at = created_at;
        this.month = month;
        this.emotion = emotion;
        this.user = user;
        this.question = question;
    }
}