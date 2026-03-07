package com.example.init_prj.mapper;


import com.example.init_prj.entity.BoardDTO;
import com.example.init_prj.entity.LoginDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LoginMapper {

    @Select("select * from notice where uid = #{uid} and pw = #{pw}")
    List<LoginDTO> login(String uid, String pw);
}
