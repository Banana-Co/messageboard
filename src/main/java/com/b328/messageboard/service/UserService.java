package com.b328.messageboard.service;

import com.b328.messageboard.entity.User;
import com.b328.messageboard.mapper.UserMapper;
import com.b328.messageboard.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
@Primary
public class UserService implements IUserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 返回所有用户
     * @return List<User>
     */
    @Override
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    /**
     * 向user表中添加一条数据
     * @param user
     * @return int
     */
    @Override
    public int addUser(User user) {
        user.setRegister_time(new Timestamp(new Date().getTime()));
        return userMapper.addUser(user);
    }

    /**
     * 修改用户的密码
     * @param user
     */
    public void changePswd(User user){
        userMapper.changePswd(user);
    }

    /**
     * 通过user_name查找用户
     * @param user_name
     * @return User
     */
    @Override
    public User getUser(String user_name) {
        return userMapper.getUser(user_name);
    }

    /**
     * 通过user_name查找用户ID
     * @param user_name
     * @return Integer
     */
    @Override
    public Integer getIdByName(String user_name){return userMapper.getIdByName(user_name);}

}
