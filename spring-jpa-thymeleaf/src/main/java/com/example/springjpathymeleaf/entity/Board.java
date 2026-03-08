package com.example.springjpathymeleaf.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "notice")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "user_id")
    int userId = 1;
    String title, content, fileName;

    @CreationTimestamp
    LocalDateTime createdAt;
}