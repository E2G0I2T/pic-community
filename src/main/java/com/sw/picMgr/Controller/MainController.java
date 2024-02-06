package com.sw.picMgr.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // html 로 응답하는 컨트롤러
@RequestMapping("")
public class MainController {
    @GetMapping("") // http://localhost:8080
    public String index() {
        return "index";
    }
}
