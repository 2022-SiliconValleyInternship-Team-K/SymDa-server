package team_k.symda.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import team_k.symda.Constants.Emotion;
import team_k.symda.Constants.Weather;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter @ToString @NoArgsConstructor
@Entity
public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diary_id;

    @Column(length = 1000)
    private String content;

    @Enumerated(EnumType.STRING)
    private Weather weather;

    private LocalDateTime created_at;
    @PrePersist
    public void created_at(){
        this.created_at = LocalDateTime.now();
    }

    @ManyToOne  // 다대일 단방향 관계
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne   // 일대일 단방향 관계
    @JoinColumn(name = "question_id")
    private Question question;

    @Enumerated(EnumType.STRING)
    private Emotion emotion;

    public Diary(Long diary_id, String content, Weather weather, LocalDateTime created_at, User user, Question question) {
        this.diary_id = diary_id;
        this.content = content;
        this.weather = weather;
        this.created_at = created_at;
        this.user = user;
        this.question = question;
    }
}
