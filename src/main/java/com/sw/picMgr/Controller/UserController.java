package com.sw.picMgr.Controller;

import com.sw.picMgr.Service.UserService;
import com.sw.picMgr.data.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;
    // 회원가입 처리
    @PostMapping("/userSave") // http://localhost:8080/api/user/userSave
    public User userSave(@RequestBody User user) {
        User u = userService.userSave(user);
        return u;
    }
}
