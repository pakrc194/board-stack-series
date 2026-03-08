package com.example.init_prj.mapper;


import com.example.init_prj.entity.BoardDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {

    @Select("""
            select * from notice order by id desc
            """)
    List<BoardDTO> list();

    @Select("select * from notice where id = #{id}")
    BoardDTO detail(int id);

    @Insert("""
        insert into notice (user_id, title, content, created_at, file_name)
        values (#{userId}, #{title}, #{content}, now(), #{fileName});
    """)
    int insert(BoardDTO board);

    @Update("""
        update notice set user_id=#{userId}, title=#{title}, content=#{content}
        where id=#{id}
    """)
    int modify(BoardDTO board);

    @Delete("""
        delete from notice where id = #{id}
    """)
    int delete(int id);
}
