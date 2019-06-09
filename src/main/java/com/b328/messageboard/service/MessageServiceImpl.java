package com.b328.messageboard.service;

import com.b328.messageboard.dao.MessageMapper;
import com.b328.messageboard.entity.Message;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("MessageService1")
public class MessageServiceImpl implements MessageService{
    @Autowired
    MessageMapper messageMapper;

    @Override
    public List<Message> getMessagePage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Message> messages = messageMapper.getAllMessages();
        return messages;
    }

    @Override
    public Message getMessageById(Integer id) {
        return messageMapper.getMessageById(id);
    }

    @Override
    public int addMessage(Message message) {
        return messageMapper.addMessage(message);
    }
}
