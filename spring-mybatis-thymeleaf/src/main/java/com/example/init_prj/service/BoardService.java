package com.example.init_prj.service;

import com.example.init_prj.entity.BoardDTO;
import com.example.init_prj.mapper.BoardMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Resource
    BoardMapper mapper;


    public List<BoardDTO> boardList() {
        return mapper.list();
    }
}
