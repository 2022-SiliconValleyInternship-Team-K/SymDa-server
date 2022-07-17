package team_k.symda.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team_k.symda.Entity.Diary;

import java.util.List;

/*
 * JpaRepository 상속 -> 자동으로 빈 등록 (@Repository 안 달아도 됨)
 * */
public interface JpaDiaryRepository extends JpaRepository<Diary, Long>, DiaryRepository {   // 인터페이스 다중 상속
    @Override
    List<Diary> findByMonth(String month);  // JPQL select m from Diary m where m.month=?
}
