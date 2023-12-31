package com.example.contact_message.mapper;

import com.example.contact_message.entity.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface MessageMapper extends BaseMapper<Message> {
    @Insert("INSERT INTO message (id, content, fromUserId, toUserId, fromUserName, toUserName) VALUES (#{id}, #{content}, #{fromUserId}, #{toUserId}, #{fromUserName}, #{toUserName})")
    int insert(Message message);

    @Select("SELECT * FROM Message WHERE toUserId = #{toUserId}")
    List<Message> selectByToUserId(@Param("toUserId") Long toUserId);

    @Select("SELECT * FROM Message")
    List<Message> selectAll();
    @Update("UPDATE Message SET content = #{message.content} WHERE id = #{message.id}")
    int updateMessage(@Param("message") Message message);

    @Delete("DELETE FROM Message WHERE id = #{id}")
    int deleteMessage(@Param("id") Long id);
}