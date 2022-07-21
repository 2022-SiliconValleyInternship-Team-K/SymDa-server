package team_k.symda.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team_k.symda.Service.UserRequest;
import team_k.symda.Service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody UserRequest request) {
        log.info("email = {}, password = {}", request.getEmail(), request.getPassword());
        if(userService.signup(request).equals("Success")) {
            return new ResponseEntity(HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserRequest request, HttpServletRequest request2) {
        log.info("email = {}, password = {}", request.getEmail(), request.getPassword());
        if(userService.login(request.getEmail(), request.getPassword()).equals("Success")) {
            return new ResponseEntity(HttpStatus.OK);
        }
        //세션이 있으면 있는 세션 반환, 없으면 신규 세션 생성
        HttpSession session = request2.getSession();
        //세션에 로그인 회원 정보 보관
        session.setAttribute("loginMember", request);
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}