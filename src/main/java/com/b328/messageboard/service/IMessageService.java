package com.b328.messageboard.service;

import com.b328.messageboard.entity.Likes;
import com.b328.messageboard.entity.Message;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IMessageService {
    List<Message> getMessagePage(Integer pageNum, Integer pageSize);

    Message getMessageById(Integer id);

    int addMessage(Message message);
    void addLike(Message message, Integer uid);
    void addDislike(Message message, Integer uid);
    boolean hasLike(Likes likes);
}
