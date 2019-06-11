package com.b328.messageboard.service;

import com.b328.messageboard.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IUserService {
    List<User> getAllUsers();
    int addUser(User user);
    void changePswd(User user);
    User getUser(String user_name);
    Integer getIdByName(String user_name);
}
