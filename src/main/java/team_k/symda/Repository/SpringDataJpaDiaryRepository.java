package team_k.symda.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team_k.symda.Entity.Diary;

import java.util.List;
import java.util.Optional;

/*
 * JpaRepository 상속 -> 자동으로 빈 등록 (@Repository 안 달아도 됨)
 * */
public interface SpringDataJpaDiaryRepository extends JpaRepository<Diary, Long>, DiaryRepository {
    @Override
    List<Diary> findByMonth(String month);

    @Override
    Optional<Diary> findByDate(String date);

    @Override
    void deleteByDate(String date);
}
