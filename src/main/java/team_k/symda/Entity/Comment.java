package team_k.symda.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comment_id;    // 코멘트 pk

    private String comment;     // 코멘트

    private String music_url;   // 노래주소

    @OneToOne
    @JoinColumn(name = "diary_id")
    private Diary diary;        // 일기 pk (FK)

    public Comment(String comment, String music_url, Diary diary) {
        this.comment = comment;
        this.music_url = music_url;
        this.diary = diary;
    }
}