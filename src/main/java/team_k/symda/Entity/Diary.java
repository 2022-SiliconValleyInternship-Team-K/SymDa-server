package team_k.symda.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import team_k.symda.Constants.Emotion;
import team_k.symda.Constants.Weather;

import javax.persistence.*;
import java.time.LocalDate;

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

    @Column(name = "created_at")
    private LocalDate created;   // 생성 시간
    @PrePersist // DB에 해당 테이블의 insert 연산을 실행할 때 같이 실행해라
    public void created(){
        this.created = LocalDate.now();
        setMonth(created);
    }

    private String month;   // 연월
    public void setMonth(LocalDate created_at) {
        String year = Integer.toString(created_at.getYear());
        String month = Integer.toString(created_at.getMonthValue());
        this.month = year+month;
    }

    @Column
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private Emotion emotion;    // 감정

    @ManyToOne  // 다대일 단방향 관계, user 삭제되면 일기도 삭제
    @JoinColumn(name = "user_id")
    private User user;  // 유저 pk (FK)

    @OneToOne   // 일대일 단방향 관계
    @JoinColumn(name = "question_id")
    private Question question;  // 질문 pk (FK)

    public Diary(String content, Weather weather, LocalDate created_at, String month, Emotion emotion, User user, Question question, String imageUrl) {
        this.content = content;
        this.weather = weather;
        this.created = created;
        this.month = month;
        this.emotion = emotion;
        this.user = user;
        this.question = question;
        this.imageUrl = imageUrl;
    }

}