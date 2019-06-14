package com.b328.messageboard.mapper;

import com.b328.messageboard.entity.Message;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageMapper {
    /**
     * 返回所有留言
     * @return  List<Message>
     */
    @Select("SELECT * FROM Message ORDER BY like_number DESC,id DESC")
    @Results(@Result(property = "createDate", column = "create_date"))
    List<Message> getAllMessages();

    /**
     * 通过id查找留言
     * @param id
     * @return Message
     */
    @Select("SELECT * FROM Message WHERE id = #{id}")
    Message getMessageById(Integer id);

    /**
     * 添加留言
     * @param message
     * @return int
     */
    @Insert("INSERT INTO Message(create_date, author, title, content) VALUES(#{createDate}, #{author}, #{title}, #{content})")
    int addMessage(Message message);

    /**
     * 改变赞
     * @param message
     * @return int
     */
    @Update("UPDATE Message SET like_number = #{like_number} where id = #{id}")
    int changeLike(Message message);
}
