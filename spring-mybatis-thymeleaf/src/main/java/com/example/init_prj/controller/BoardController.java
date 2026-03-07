package com.example.init_prj.controller;

import com.example.init_prj.service.BoardService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Resource
    BoardService boardService;


    @GetMapping("list")
    String list(Model md) {
        md.addAttribute("boardList", boardService.boardList());

        return "list";
    }

    @GetMapping("detail/{id}")
    String detail(Model md, @PathVariable("id")int id) {
        md.addAttribute("board", boardService.boardDetail(id));

        return "detail";
    }

    @GetMapping("insert")
    String insert() {

        return "insert";
    }
}
