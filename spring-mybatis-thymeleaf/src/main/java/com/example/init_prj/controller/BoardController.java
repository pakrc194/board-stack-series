package com.example.init_prj.controller;

import com.example.init_prj.entity.BoardDTO;
import com.example.init_prj.service.BoardService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

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

    @GetMapping("detail/{id}")
    String detail(Model md, @PathVariable int id) {
        md.addAttribute("board", boardService.boardDetail(id));

        return "detail";
    }

    @GetMapping("insert")
    String insert() {

        return "insert";
    }

    @PostMapping("insert")
    String insertResult(BoardDTO board) {
        // 파일이 있을 때만 처리
        if (board.getFile() != null && !board.getFile().isEmpty()) {
            String originalName = board.getFile().getOriginalFilename();
            // 파일명 중복 방지 - UUID 사용
            String savedName = UUID.randomUUID() + "_" + originalName;

            try {
                File dir = new File(uploadPath);
                if (!dir.exists()) dir.mkdirs(); // 폴더 없으면 생성

                board.getFile().transferTo(new File(uploadPath + savedName));
                board.setFileName(savedName); // DB에 저장할 파일명 세팅
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        boardService.boardInsert(board);
        return "redirect:/board/list";
    }

    @GetMapping("modify/{id}")
    String modify(Model md, @PathVariable int id) {
        md.addAttribute("board", boardService.boardDetail(id));
        return "modify";
    }

    @PostMapping("modify")
    String modifyResult(BoardDTO board) {
        boardService.boardModify(board);
        return "redirect:/board/detail/"+board.getId();
    }

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
