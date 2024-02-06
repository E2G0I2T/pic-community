package com.sw.picMgr.Controller;

import com.sw.picMgr.Service.UserService;
import com.sw.picMgr.data.entity.User;
import com.sw.picMgr.data.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/html/user")
public class Html_UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;


    // 회원가입 폼 보내줌
    @GetMapping("/userForm") // ....../html/user/userForm
    public String userForm() {
        return "userForm"; // userForm.html 띄워줌
    }


    // 회원가입 처리
    @PostMapping("/userSave") // http://localhost:8080/html/user/userSave
    // public User userSave(@RequestBody User user) {
    public String userSave(User user) {
        User u = userService.userSave(user);
        return "index";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "list";
    }

    // 로그인 폼 보내줌
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // 로그인 체크
    @PostMapping("loginCheck")
    public String loginCheck(User user, HttpServletRequest request) {
        String rr = userService.loginCheck(user);
        HttpSession session = request.getSession(); // 세션 객체 얻기
        if (rr.equals("YES")) {
            session.setAttribute("loginTag", "YES");
        } else {
            session.setAttribute("loginTag", "NO");
        }
        return "index";
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(); // 세션 객체 얻기
        session.setAttribute("loginTag", "NO");
        return "index";
    }
}
