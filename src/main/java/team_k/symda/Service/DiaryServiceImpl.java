package team_k.symda.Service;

import org.springframework.beans.factory.annotation.Autowired;
import com.mysql.cj.AbstractQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import team_k.symda.Constants.Emotion;
import team_k.symda.Entity.Comment;
import team_k.symda.Entity.Diary;
import team_k.symda.Entity.Question;
import team_k.symda.Entity.User;
import team_k.symda.Repository.CommentRepository;
import team_k.symda.Repository.DiaryRepository;
import team_k.symda.Repository.QuestionRepository;
import team_k.symda.Repository.UserRepository;
import team_k.symda.domain.DiaryCreateRequestDto;
import team_k.symda.domain.DiaryResponseDto;
import team_k.symda.domain.MonthlyEmotionDiaryResponseDto;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Service
public class DiaryServiceImpl implements DiaryService{

    // 의존성 주입 (DI)
    private final DiaryRepository diaryRepository;
    private final QuestionRepository questionRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private S3Uploader s3Uploader;
    @Autowired
    public DiaryServiceImpl(DiaryRepository diaryRepository, QuestionRepository questionRepository, CommentRepository commentRepository, UserRepository userRepository, S3Uploader s3Uploader) {
        this.diaryRepository = diaryRepository;
        this.questionRepository = questionRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.s3Uploader = s3Uploader;
    }

    /*
     * 일기 작성
     * */
    @Override @Transactional
    public void keepDiary(MultipartFile image, Diary diary) throws IOException {
        // DiaryCreateRequestDto <-> Entity
        // userId로 참조하는 user 가져옴 (FK)
//        Optional<User> userById = userRepository.findById(diaryCreateRequestDto.getUserId());
//        User user = userById.orElseThrow();
//
//        // questionId로 참조하는 question 가져옴 (FK)
//        Optional<Question> questionById = questionRepository.findById(diaryCreateRequestDto.getQuestionId());
//        Question question = questionById.orElseThrow();

        // diaryCreateRequestDto의 weather과 emotion으로 적절한 comment 가져옴 (FK)
        Optional<Comment> commentByEmotionAndWeather = commentRepository.findByEmotionAndWeather(diary.getEmotion(), diary.getWeather());
        Comment comment = commentByEmotionAndWeather.orElseThrow();
        System.out.println(comment);
        diary.setComment(comment);

        //Diary diary = diaryCreateRequestDto.toEntity(user, question, comment);

        Optional<User> UserById = userRepository.findById(diary.getUser_id());
        User user = UserById.orElseThrow();

        Optional<Question> QuestionById = questionRepository.findById(diary.getQuestion_id());
        Question question = QuestionById.orElseThrow();

        diary.setUser(user);
        diary.setQuestion(question);

        // image 처리
        if(!image.isEmpty()) {  // image가 비어있지 않음
            String storedFileName = s3Uploader.upload(image,"images");
            diary.setImageUrl(storedFileName);
        }
        
        // Diary Entity 저장
        Diary savedDiary = diaryRepository.save(diary);

        // Entity를 응답 Dto (user 정보 안주기 위해)로 변환해서 클라이언트에게 줌
        DiaryResponseDto diaryResponseDto = savedDiary.diaryToDiaryResponseDto(savedDiary);
        //return diaryResponseDto;

    }
    /*
     * id로 일기 삭제
     * */
    @Override @Transactional
    public void deleteDiary(Long diary_id) {
        diaryRepository.deleteById(diary_id);
    }

    /*
     * date로 일기 삭제
     * */
    @Override @Transactional
    public void deleteDiaryByDate(String date) {
        diaryRepository.deleteByDate(date);
    }

    /*
     * 개별 일기 조회 - id로
     * */
    @Override @Transactional
    public DiaryResponseDto findDiary(Long diary_id) {
        Optional<Diary> find_diary = diaryRepository.findById(diary_id);
        Diary diary = find_diary.orElseThrow();
        DiaryResponseDto diaryResponseDto = diary.diaryToDiaryResponseDto(diary);
        return diaryResponseDto;
    }

    /*
     * 개별 일기 조회 - date로
     * */
    @Override @Transactional
    public DiaryResponseDto findDiaryByDate(String date) {
        Optional<Diary> find_diaryByDate = diaryRepository.findByDate(date);
        Diary diary = find_diaryByDate.orElseThrow();
        DiaryResponseDto diaryResponseDto = diary.diaryToDiaryResponseDto(diary);
        return diaryResponseDto;
    }

    /*
     * 월별 일기 조회
     * */
    @Override @Transactional
    public List<DiaryResponseDto> findMonthlyDiary(String month) {
        List<Diary> diary_byMonth = diaryRepository.findByMonth(month);

        // Entity -> responseDto로 바꾸기
        List<DiaryResponseDto> monthlyDiary = new ArrayList<>();
        for (Diary diary: diary_byMonth){
            monthlyDiary.add(diary.diaryToDiaryResponseDto(diary));
        }
        return monthlyDiary;
    }

    /*
     * 월별 일기 감정 조회
     * */
    @Override @Transactional
    public List<MonthlyEmotionDiaryResponseDto> findMonthlyEmotion(String month) {
        List<Diary> diary_byMonth = diaryRepository.findByMonth(month);

        // Entity -> responseDto로 바꾸기
        List<MonthlyEmotionDiaryResponseDto> monthlyDiary = new ArrayList<>();
        for (Diary diary: diary_byMonth) {
            monthlyDiary.add(diary.diaryToMonthlyEmotionDiaryResponseDto(diary));
        }

        return monthlyDiary;
    }

    /*
     * 식물 상태 조회 (월별 일기 개수 조회)
     * */
    @Override @Transactional
    public int cntMonthlyPlant(String month) {
        return diaryRepository.findByMonth(month).size();
    }

    @Override @Transactional
    public String findTop1Diary(User user_id) {
        Diary diary_orderByDiaryId = diaryRepository.findTop1ByUserOrderByCreatedDesc(user_id);
        String content = diary_orderByDiaryId.getContent();
        return content;
    }
}
