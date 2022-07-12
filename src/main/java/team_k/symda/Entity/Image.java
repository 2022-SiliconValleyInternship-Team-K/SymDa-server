package team_k.symda.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter @ToString @NoArgsConstructor
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long image_id;

    private String image;

    private LocalDateTime created_at;
    @PrePersist
    public void created_at(){
        this.created_at = LocalDateTime.now();
    }

    @OneToOne   // 일대일 단방향 관계
    @JoinColumn(name = "diary_id")
    private Diary diary;

    public Image(Long image_id, String image, LocalDateTime created_at, Diary diary) {
        this.image_id = image_id;
        this.image = image;
        this.created_at = created_at;
        this.diary = diary;
    }
}
