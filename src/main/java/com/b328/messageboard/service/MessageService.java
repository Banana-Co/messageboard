package com.b328.messageboard.service;

import com.b328.messageboard.entity.Message;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface MessageService {
    List<Message> getMessagePage(Integer pageNum, Integer pageSize);

    Message getMessageById(Integer id);

    int addMessage(Message message);
}
