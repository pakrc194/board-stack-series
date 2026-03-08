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
    public BoardDTO boardDetail(int id) {return mapper.detail(id);}
    public int boardInsert(BoardDTO board) {
        return mapper.insert(board);
    }
    public int boardDelete(int id) {
        return mapper.delete(id);
    }
    public int boardModify(BoardDTO board) {
        return mapper.modify(board);
    }
}
