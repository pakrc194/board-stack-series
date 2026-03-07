package com.example.init_prj.service;

import com.example.init_prj.entity.LoginDTO;
import com.example.init_prj.mapper.LoginMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {
    @Resource
    LoginMapper mapper;

    public List<LoginDTO> loginAuth(LoginDTO user) {

        return mapper.login(user.getUid(), user.getPw());
    }
}
