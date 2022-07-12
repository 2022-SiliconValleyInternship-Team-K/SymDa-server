package team_k.symda.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter @Setter @ToString @NoArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comment_id;

    private String comment;

    private String music_url;

    @OneToOne
    @JoinColumn(name = "diary_id")
    private Diary diary;

    public Comment(Long comment_id, String comment, String music_url, Diary diary) {
        this.comment_id = comment_id;
        this.comment = comment;
        this.music_url = music_url;
        this.diary = diary;
    }
}
