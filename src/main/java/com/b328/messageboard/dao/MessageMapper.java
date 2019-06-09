package com.b328.messageboard.dao;

import com.b328.messageboard.entity.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageMapper {
    @Select("SELECT * FROM Message ORDER BY id DESC")
    @Results(@Result(property = "createDate", column = "create_date"))
    List<Message> getAllMessages();

    @Select("SELECT * FROM Message WHERE id = #{id}")
    Message getMessageById(Integer id);

    @Insert("INSERT INTO Message(create_date, author, title, content) VALUES(NOW(), #{author}, #{title}, #{content})")
    int addMessage(Message message);
}
