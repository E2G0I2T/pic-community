package com.sw.picMgr.data.repository;

import com.sw.picMgr.data.entity.Pic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PicRepository extends JpaRepository<Pic, Long> {
    Optional<Pic> findByTitle(String title);

}
