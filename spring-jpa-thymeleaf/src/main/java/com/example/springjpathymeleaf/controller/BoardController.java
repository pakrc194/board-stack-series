package com.example.springjpathymeleaf.controller;

import com.example.springjpathymeleaf.entity.Board;
import com.example.springjpathymeleaf.service.BoardService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Resource
    BoardService boardService;
    @Value("${file.upload.path}")
    String uploadPath;

    @GetMapping("list")
    String list(Model md) {
        md.addAttribute("boardList", boardService.boardList());
        return "list";
    }

    // 상세
    @GetMapping("detail/{id}")
    String detail(Model md, @PathVariable int id) {
        md.addAttribute("board", boardService.boardDetail(id));
        return "detail";
    }

    // 등록 폼
    @GetMapping("insert")
    String insert() {
        return "insert";
    }

    // 등록 처리
    @PostMapping("insert")
    String insertResult(Board board, @RequestParam(required = false) MultipartFile file) throws IOException {
        boardService.boardInsert(board, file);
        return "redirect:/board/list";
    }

    // 수정 폼
    @GetMapping("modify/{id}")
    String modify(Model md, @PathVariable int id) {
        md.addAttribute("board", boardService.boardDetail(id));
        return "modify";
    }

    // 수정 처리
    @PostMapping("modify")
    String modifyResult(Board board) {
        boardService.boardModify(board);
        return "redirect:/board/detail/" + board.getId();
    }

    // 삭제
    @GetMapping("delete/{id}")
    String delete(@PathVariable int id) {
        boardService.boardDelete(id);
        return "redirect:/board/list";
    }

    @GetMapping("download/{fileName}")
    ResponseEntity<org.springframework.core.io.Resource> download(@PathVariable String fileName) throws IOException {
        Path path = Paths.get(uploadPath + fileName);
        org.springframework.core.io.Resource resource = new UrlResource(path.toUri());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }
}
