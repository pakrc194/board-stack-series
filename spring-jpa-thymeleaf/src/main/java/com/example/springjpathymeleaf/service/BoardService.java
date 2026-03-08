package com.example.springjpathymeleaf.service;

import com.example.springjpathymeleaf.entity.Board;
import com.example.springjpathymeleaf.repository.BoardRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class BoardService {
    @Resource
    BoardRepository boardRepository;
    @Value("${file.upload.path}")
    String uploadPath;
    // 목록
    public List<Board> boardList() {
        return boardRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    // 상세
    public Board boardDetail(int id) {
        return boardRepository.findById(id).orElseThrow();
    }

    // 등록
    public void boardInsert(Board board, MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            String savedName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            File dir = new File(uploadPath);
            if (!dir.exists()) dir.mkdirs();
            file.transferTo(new File(uploadPath + savedName));
            board.setFileName(savedName);
        }
        boardRepository.save(board);
    }

    // 수정
    public void boardModify(Board board) {
        Board target = boardRepository.findById(board.getId()).orElseThrow();
        target.setTitle(board.getTitle());
        target.setContent(board.getContent());
        boardRepository.save(target);
    }

    // 삭제
    public void boardDelete(int id) {
        boardRepository.deleteById(id);
    }
}