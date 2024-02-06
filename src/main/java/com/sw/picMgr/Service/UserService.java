package com.sw.picMgr.Service;

import com.sw.picMgr.data.entity.User;
import com.sw.picMgr.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service // 객체(UserService)가 생성되어서 스프링부트 컨테이너에 저장됨
public class UserService {
    @Autowired
    UserRepository userRepository;
    // 회원 저장하는 메서드
    public User userSave(User user) {
        // 1. 기존 가입 회원인지 검사
        String email = user.getEmail();
        Optional<User> selUser = userRepository.findByEmail(email);
        if (selUser.isPresent()) { // 이미 가입된 회원
            System.out.println("=== 이미 가입됨 ===");
            return null;
        } else { // 신규 회원
            // 2. 기존 가입 회원이 아니면 저장
            User u = userRepository.save(user);
            return u;
        }
    }

    public String loginCheck(User user) {
        String email = user.getEmail();
        Optional<User> selUser = userRepository.findByEmail(email);
        if (selUser.isPresent()) {
            User uu = selUser.get();
            if (user.getPassword().equals(uu.getPassword())) {
                System.out.println("====> 회원 맞음");
                return "YES";
            }
        }
        return "NO";
    }
}
