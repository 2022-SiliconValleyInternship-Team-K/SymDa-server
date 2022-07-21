package team_k.symda.Repository;

import team_k.symda.Entity.Diary;
import team_k.symda.Entity.User;

import java.util.List;
import java.util.Optional;

public interface DiaryRepository {
    /*
     * Diary 저장 (C)
     */
    Diary save(Diary diary);

    /*
     * diary_id로 Diary 찾기 (R)
     * */
    Optional<Diary> findById(Long id);

    /*
     * 월별 일기 반환 (R)
     * */
    List<Diary> findByMonth(String month);

    /*
     * id로 Diary 삭제 (D)
     * */
    void deleteById(Long id);

    /*
     * 유저의 일기 1개 반환 (R)
     * */
    Diary findTop1ByUserOrderByCreatedDesc(User userId);
}