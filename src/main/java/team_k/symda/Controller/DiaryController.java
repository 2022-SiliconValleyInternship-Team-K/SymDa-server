package team_k.symda.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team_k.symda.Constants.Emotion;
import team_k.symda.Entity.Diary;
import team_k.symda.Entity.Question;
import team_k.symda.Service.DiaryService;
import team_k.symda.Service.QuestionService;
import team_k.symda.Service.UserRequest;
import team_k.symda.domain.DiaryCreateRequestDto;
import team_k.symda.domain.DiaryResponseDto;
import team_k.symda.domain.MonthlyEmotionDiaryResponseDto;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class DiaryController {

    // 의존성 주입 (DI)
    private final DiaryService diaryService;
    private final QuestionService questionService;

    @Autowired
    public DiaryController(DiaryService diaryService, QuestionService questionService) {
        this.diaryService = diaryService;
        this.questionService = questionService;
    }

    /*
     * 일기 작성 - POST
     * */
    @ResponseBody   // Long 타입을 리턴하고 싶은 경우 붙여야 함 (Long - 객체)
    @PostMapping(value="/diary/new",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    // form-data/multipart -> 파라미터 처리 형식으로 받기
    public void saveDiary(@RequestParam(value="image") MultipartFile image, @ModelAttribute Diary diary) throws IOException {
        // @ModelAttribute 생략 가능하지만,, 헷갈릴까봐 추가
        System.out.println("DiaryController.saveDiary");
        System.out.println(image);
        System.out.println(diary);
        System.out.println("------------------------------------------------------");

        diaryService.keepDiary(image, diary);
        //return diaryResponseDto;
    }

    /*
     * id로 일기 삭제 - DELETE
     * */
    @ResponseBody
    @DeleteMapping("/diary/{diaryId}")
    public void deleteDiary(@PathVariable Long diaryId){
        diaryService.deleteDiary(diaryId);
    }

    /*
     * date로 일기 삭제 - DELETE
     * */
    @ResponseBody
    @DeleteMapping("/diary/date/{date}/delete")
    public void deleteDiaryByDate(@PathVariable String date){
        diaryService.deleteDiaryByDate(date);
    }

    /*
     * id로 일기 조회 - GET
     * */
    @ResponseBody
    @GetMapping("/diary/{diaryId}")
    public DiaryResponseDto getDiary(@PathVariable Long diaryId){
        DiaryResponseDto diary = diaryService.findDiary(diaryId);
        return diary;
    }

    /*
     * date 로 일기 조회 - GET
     * */
    @ResponseBody
    @GetMapping("/diary/date/{date}")
    public DiaryResponseDto getDiaryByDate(@PathVariable String date){
        DiaryResponseDto diaryByDate = diaryService.findDiaryByDate(date);
        return diaryByDate;
    }

    /*
     * 월별 일기 조회 - GET
     * */
    @ResponseBody
    @GetMapping("/diary/monthly/{month}")
    public List<DiaryResponseDto> getMonthlyDiary(@PathVariable String month){
        List<DiaryResponseDto> monthlyDiary = diaryService.findMonthlyDiary(month);
        return monthlyDiary;
    }

    /*
     * 월별 감정 조회 - GET
     * */
    @ResponseBody
    @GetMapping("/diary/monthly/{month}/emotion")
    public List<MonthlyEmotionDiaryResponseDto> getMonthlyEmotion(@PathVariable String month){
        List<MonthlyEmotionDiaryResponseDto> monthlyEmotion = diaryService.findMonthlyEmotion(month);
        return monthlyEmotion;
    }


    /*
     * 식물 상태 조회 - GET
     * */
    @ResponseBody
    @GetMapping("/diary/monthly/{month}/plant")
    public int getMonthlyPlant(@PathVariable String month){
        int plant = diaryService.cntMonthlyPlant(month);
        return plant;
    }

    /*
     * 오늘의 질문 조회 - GET
     * */
    @ResponseBody
    @GetMapping("/diary/new/question/{questionId}")
    public Question getTodayQuestion(@PathVariable Long questionId){
        Question question = questionService.findQuestion(questionId);
        return question;
    }

    /*
     * 유저의 새 일기 내용 1개 조회 - GET
     * */
    @ResponseBody
    @GetMapping("/diary/content")
    public String getDiaryContent(HttpServletRequest request){
        HttpSession session = request.getSession();
        UserRequest user = (UserRequest) session.getAttribute("loginMember");
        String diary = diaryService.findTop1Diary(user.getUser_id());
//      String diary = diaryService.findTop1Diary(user_id);
        return diary;
    }

}