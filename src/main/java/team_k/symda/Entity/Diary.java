package team_k.symda.Entity;

import lombok.*;
import team_k.symda.Constants.Emotion;
import team_k.symda.Constants.Weather;
import team_k.symda.domain.DiaryResponseDto;
import team_k.symda.domain.MonthlyEmotionDiaryResponseDto;

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
    public void createdAt(){
        this.created = LocalDate.now();
        setMonth(created);
        setDate(created);
    }

    private String month;   // 연월
    public void setMonth(LocalDate createdAt) {
        String year = Integer.toString(createdAt.getYear());
        String month = String.format("%02d", createdAt.getMonthValue());
        this.month = year+month;
    }

    private String date;
    public void setDate(LocalDate createdAt){
        String year = Integer.toString(createdAt.getYear());
        String month = String.format("%02d", createdAt.getMonthValue());
        String day = String.format("%02d", createdAt.getDayOfMonth());
        this.date = year + month + day;
    }

    @Column
    private String imageUrl;    // 이미지 주소

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Enumerated(EnumType.STRING)
    private Emotion emotion;    // 감정

    @ManyToOne  // 다대일 단방향 관계, user 삭제되면 일기도 삭제
    @JoinColumn(name = "user_id")
    private User user;  // 유저 pk (FK)

    @OneToOne   // 일대일 단방향 관계
    @JoinColumn(name = "question_id")
    private Question question;  // 질문 pk (FK)

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @Builder
    public Diary(String content, Weather weather, LocalDate created, String month, String date, String imageUrl, Emotion emotion, User user, Question question, Comment comment) {
        this.content = content;
        this.weather = weather;
        this.created = created;
        this.month = month;
        this.date = date;
        this.imageUrl = imageUrl;
        this.emotion = emotion;
        this.user = user;
        this.question = question;
        this.comment = comment;
    }

    // Diary -> DiaryResponseDto
    public DiaryResponseDto diaryToDiaryResponseDto(Diary diary){
        return DiaryResponseDto.builder()
                .diary_id(diary_id)
                .content(content)
                .weather(weather)
                .createdAt(created)
                .month(month)
                .date(date)
                .emotion(emotion)
                .question(question)
                .comment(comment)
                .imageUrl(imageUrl).build();
    }

    // Diary -> MonthlyEmotionDiaryResponseDto
    public MonthlyEmotionDiaryResponseDto diaryToMonthlyEmotionDiaryResponseDto(Diary diary){
        return MonthlyEmotionDiaryResponseDto.builder()
                .diary_id(diary_id)
                .date(date)
                .emotion(emotion).build();
    }
}