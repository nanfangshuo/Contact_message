package com.example.contact_message.mapper;

import com.example.contact_message.entity.Contact;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ContactMapper {
    @Select("SELECT * FROM Contact WHERE userId = #{userId}")
    List<Contact> selectByUserId(@Param("userId") Long userId);

    @Update("UPDATE Contact SET avatar = #{contact.avatar}, name = #{contact.name}, phoneNumber = #{contact.phoneNumber} WHERE id = #{contact.id}")
    int updateContact(@Param("contact") Contact contact);

    @Delete("DELETE FROM Contact WHERE id = #{id}")
    int deleteContact(@Param("id") Long id);

    @Insert("INSERT INTO contact (userId, name, phoneNumber, avatar) VALUES (#{userId}, #{name}, #{phoneNumber}, #{avatar})")
    int insertContact(Contact contact);

    @Select("SELECT * FROM Contact WHERE userId = #{userId} AND name LIKE CONCAT('%', #{keyword}, '%')")
    List<Contact> searchByKeywordAndUserId(@Param("keyword") String keyword, @Param("userId") Long userId);
}