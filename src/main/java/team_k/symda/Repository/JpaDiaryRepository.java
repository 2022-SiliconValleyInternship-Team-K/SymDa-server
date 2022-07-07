package team_k.symda.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team_k.symda.Entity.Diary;

import java.time.LocalDate;
import java.util.List;

public interface JpaDiaryRepository extends JpaRepository<Diary, Long>, DiaryRepository {   // 인터페이스 다중 상속

    @Override
    List<Diary> findByMonth(String month);
}
