package com.example.init_prj.entity;

import lombok.Data;

@Data
public class BoardDTO {
    int id, userId;
    String title, content, createdAt, fileName;
    boolean pinned;
}
