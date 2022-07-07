package team_k.symda.Repository;

import team_k.symda.Entity.Diary;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DiaryRepository {
    /*
    * Diary 저장
    * */
    Diary save(Diary diary);

    /*
     * id로 Diary 찾기
     * id가 null일 경우를 대비해 optional로 감싸서 반환
     * */
    Optional<Diary> findById(Long id);

    /*
     * 월별 일기 반환
     * */
    List<Diary> findByMonth(String month);

    /*
     * id로 Diary 삭제
     * */    
    void deleteById(Long id);
}
