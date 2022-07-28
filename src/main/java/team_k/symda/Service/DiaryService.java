package team_k.symda.Service;

import org.springframework.web.multipart.MultipartFile;
import team_k.symda.Constants.Emotion;
import team_k.symda.Entity.Diary;
import team_k.symda.Entity.User;
import team_k.symda.domain.DiaryCreateRequestDto;
import team_k.symda.domain.DiaryResponseDto;
import team_k.symda.domain.MonthlyEmotionDiaryResponseDto;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DiaryService {
    /*
     * 일기 작성
     * */
    public void keepDiary(MultipartFile image, Diary diary) throws IOException;

    /*
     * id로 일기 삭제
     * */
    public void deleteDiary(Long diary_id);

    /*
     * date로 일기 삭제
     * */
    public void deleteDiaryByDate(String date);

    /*
     * 개별 일기 조회 - id로
     * */
    public DiaryResponseDto findDiary(Long diary_id);

    /*
     * 개별 일기 조회 - date로
     * */
    public DiaryResponseDto findDiaryByDate(String date);

    /*
     * 월별 일기 조회
     * */
    public List<DiaryResponseDto> findMonthlyDiary(String month);

    /*
     * 월별 일기 감정 조회
     * */
    public List<MonthlyEmotionDiaryResponseDto> findMonthlyEmotion(String month);

    /*
     * 식물 상태 조회 (월별 일기 개수 조회)
     * */
    public int cntMonthlyPlant(String month);

    /*
     * 유저의 새 일기 1개 조회
     * */
    public String findTop1Diary(User user_id);
}
