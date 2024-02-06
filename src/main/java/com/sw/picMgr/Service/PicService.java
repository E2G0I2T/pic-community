package com.sw.picMgr.Service;

import com.sw.picMgr.data.dto.PicJoinDto;
import com.sw.picMgr.data.entity.Pic;
import com.sw.picMgr.data.repository.PicRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PicService {

    @Value("${uploadPath}")
    private String UPLOAD_LOCATION; // C:/Images
    @Autowired
    PicRepository picRepository;

    public String joinOneImage(PicJoinDto dto, MultipartFile fileOne) {
        Pic pic = new Pic();
        pic.setTitle(dto.getTitle());
        pic.setDescription(dto.getDescription());

        // 2. 이미지 저장 : C:/Images 저장
        String uuidFile = fileOneSave(fileOne); // 책이미지 저장
        pic.setImage("/images/"+ uuidFile); // 이미지 이름 저장시 반드시 /images(외부에 공개된 url임)추가해야 함
        picRepository.save(pic);
        return "SUCCESS";

    }
    public String fileOneSave(MultipartFile file) {
        String uuidFile = null;
        String fileName = file.getOriginalFilename();
        String fileExt = fileName.substring(fileName.lastIndexOf("."));
        log.info("UPLOAD_LOCATION : {}", UPLOAD_LOCATION); // C:/Images
        log.info("파일 이름 : {}", fileName); log.info("파일 확장자 : {}", fileExt); // .jpg 등
        log.info("파일 크기 : {}", file.getSize());
        uuidFile = UUID.randomUUID().toString().replaceAll("-", "") + fileExt; // dcacbbcad6d84168a145b5caac334766.jpg
        log.info("UUID 파일명 : {}", uuidFile);
        String uploadFile = UPLOAD_LOCATION + "/" + uuidFile; // C:/Images/dcacbbcad6d84168a145b5caac334766.jpg
        log.info("업로드 파일 : {}", uploadFile);
        try {
            if (file.isEmpty()) {
                throw new IOException("common.file.empty"); // 빈 파일입니다.
            }

            InputStream src = file.getInputStream();
            Path dest = Paths.get(uploadFile);
            log.info("src : {}", src);
            log.info("dest : {}", dest);
            Files.copy(src,dest, StandardCopyOption.REPLACE_EXISTING ); // java.nio.file.* 필요
        } catch (IOException e) {
            throw new RuntimeException("fileOne Save Error"+e.getMessage());
        }
        return uuidFile;
    }

    public List<Pic> getAll() {
        List<Pic> pics = picRepository.findAll();
        return pics;
    }

    public Pic getOnePic(Long id) {
        Optional<Pic> opic = picRepository.findById(id);
        if(opic.isPresent()) {
            Pic pic = opic.get();
            return pic;
        }
        else {
            return null;
        }
    }
}