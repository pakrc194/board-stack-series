package com.example.init_prj.controller;

import com.example.init_prj.entity.LoginDTO;
import com.example.init_prj.service.LoginService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Resource
    LoginService loginService;

    @GetMapping
    String login() {
        return "login";
    }

    @PostMapping
    String loginAuth(Model md, LoginDTO user) {
        List<LoginDTO> list = loginService.loginAuth(user);

        if(!list.isEmpty()) {
            md.addAttribute("user", list.getFirst());
            return "loginSuccess";
        } else {
            return "loginFail";
        }
    }
}
