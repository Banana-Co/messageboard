package com.b328.messageboard.mapper;

import com.b328.messageboard.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
    /**
     * 返回所有用户
     * @return  List<User>
     */
    @Select("SELECT * FROM User")
    @Results({
            @Result(property = "user_id", column = "user_id"),
            @Result(property = "user_name", column = "user_name"),
            @Result(property = "user_password", column = "user_password")
    })
    List<User> getAllUsers();

    /**
     * 添加用户
     * @param user
     * @return int
     */
    @Insert("INSERT INTO User(user_name,user_password,register_time) VALUES(#{user_name}, #{user_password}, #{register_time})")
    int addUser(User user);

    /**
     * 返回用户
     * @param user_name
     * @return User
     */
    @Select("SELECT * FROM User WHERE user_name = #{user_name}")
    User getUser(String user_name);

    /**
     * 修改密码
     * @param user
     * @return int
     */
    @Update("UPDATE User SET user_password = #{user_password} where user_name = #{user_name}")
    int changePswd(User user);

    /**
     * 通过ID查找用户
     * @param user_id
     * @return User
     */
    @Select("SELECT * FROM User WHERE user_id = #{user_id}")
    User selectUserById(long user_id);

    /**
     * 通过user_name查找用户
     * @param user_name
     * @return Integer
     */
    @Select("SELECT user_id FROM User WHERE user_name = #{user_name}")
    Integer getIdByName(String user_name);
}
