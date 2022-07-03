package team_k.symda.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter @Setter @ToString
@Entity
public class Emotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emotion_id;

    @Enumerated(EnumType.STRING)
    private Emotion emotion;

    @OneToOne   // 일대일 단방향 관계
    @JoinColumn(name = "diary_id")
    private Diary diary;

    public Emotion(Long emotion_id, Emotion emotion, Diary diary) {
        this.emotion_id = emotion_id;
        this.emotion = emotion;
        this.diary = diary;
    }
}
