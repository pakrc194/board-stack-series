package com.example.springjpathymeleaf.repository;

import com.example.springjpathymeleaf.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Integer> {
}