package com.sw.picMgr.Controller;

import com.sw.picMgr.Service.PicService;
import com.sw.picMgr.data.dto.PicJoinDto;
import com.sw.picMgr.data.entity.Pic;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@Slf4j  // 로그 기록용
@MultipartConfig(maxFileSize = 1024*1024*2, maxRequestSize = 1024 * 1024 * 10)  //이미지의 최대 사이즈 지정
@RequestMapping("/html/pic")
public class PicController {

    @Autowired
    PicService picService;

    // 사진 업로 폼 보내기
    @GetMapping("/upload")
    public String upload() {
        return "upload"; // upload.html
    }

    @PostMapping("/picRegister")
    public String picRegister(PicJoinDto dto, @RequestParam("fileOne") MultipartFile fileOne)
            throws ServletException, IOException {
        log.info("[picController]=>post joinOneImage(책저장) 처리");
        System.out.println("title:" + dto.getTitle());
        System.out.println("description:" + dto.getDescription());

        picService.joinOneImage(dto, fileOne);
        return "index";
    }

    @GetMapping("/getAll")
    public String getAll(Model model) {
        // model : 컨트롤러의 정보를 html 로 전달하기 위한 객체
        List<Pic> pics = picService.getAll();
        model.addAttribute("pics", pics);

        return "community";
    }

    @GetMapping("/getOnePic")
    public String getOnePic(String id, Model model) {
        Long lid = Long.parseLong(id);
        Pic pic = picService.getOnePic(lid);
        model.addAttribute("pic", pic);

        return "detail"; // detail.html
    }
}
