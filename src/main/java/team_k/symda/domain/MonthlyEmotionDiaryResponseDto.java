package team_k.symda.domain;

import team_k.symda.Constants.Emotion;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MonthlyEmotionDiaryResponseDto {
    // id
    private Long diary_id;

    // date
    private String date;

    // emotion
    private Emotion emotion;

    @Builder
    public MonthlyEmotionDiaryResponseDto(Long diary_id, String date, Emotion emotion) {
        this.diary_id = diary_id;
        this.date = date;
        this.emotion = emotion;
    }
}
