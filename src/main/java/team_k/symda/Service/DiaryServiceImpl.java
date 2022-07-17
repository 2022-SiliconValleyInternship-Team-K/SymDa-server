package team_k.symda.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team_k.symda.Constants.Emotion;
import team_k.symda.Entity.Diary;
import team_k.symda.Repository.DiaryRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DiaryServiceImpl implements DiaryService{

    // 의존성 주입
    private final DiaryRepository diaryRepository;

    public DiaryServiceImpl(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    @Override @Transactional
    public Long keepDiary(Diary diary) {
        Diary savedDiary = diaryRepository.save(diary);
        return savedDiary.getDiary_id();
    }

    @Override @Transactional
    public void deleteDiary(Long diary_id) {
        diaryRepository.deleteById(diary_id);
    }

    @Override @Transactional
    public Optional<Diary> findDiary(Long diary_id) {
        Optional<Diary> find_diary = diaryRepository.findById(diary_id);
        return find_diary;
    }

    @Override @Transactional
    public List<Diary> findMonthlyDiary(String month) {
        List<Diary> diary_byMonth = diaryRepository.findByMonth(month);
        return diary_byMonth;
    }

    @Override @Transactional
    public Map<Long, Emotion> findMonthlyEmotion(String month) {
        List<Diary> diary_byMonth = diaryRepository.findByMonth(month);
        //List<Emotion> emotions_byMonth = new ArrayList<Emotion>();
        Map<Long, Emotion> emotions_byMonth = new HashMap<>();

        for (Diary diary: diary_byMonth) {
            //emotions_byMonth.add(diary.getEmotion());
            emotions_byMonth.put(diary.getDiary_id(), diary.getEmotion());
        }

        return emotions_byMonth;
    }

    @Override @Transactional
    public int cntMonthlyPlant(String month) {
        List<Diary> diary_byMonth = diaryRepository.findByMonth(month);
        int plant = diary_byMonth.size();
        return plant;
    }
}