package com.example.init_prj.mapper;


import com.example.init_prj.entity.BoardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BoardMapper {

    @Select("""
            select * from notice
            """)
    List<BoardDTO> list();
}
