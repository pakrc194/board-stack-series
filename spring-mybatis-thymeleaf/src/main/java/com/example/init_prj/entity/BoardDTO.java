package com.example.init_prj.entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class BoardDTO {
    int id, userId;
    String title, content, createdAt, fileName;
    MultipartFile file;
    boolean pinned;
}
