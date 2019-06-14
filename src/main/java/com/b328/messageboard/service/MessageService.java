package com.b328.messageboard.service;

import com.b328.messageboard.entity.Likes;
import com.b328.messageboard.mapper.LikesMapper;
import com.b328.messageboard.mapper.MessageMapper;
import com.b328.messageboard.entity.Message;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
@Qualifier("MessageService1")
public class MessageService implements IMessageService {
    @Autowired
    MessageMapper messageMapper;
    @Autowired
    LikesMapper likesMapper;

    /**
     * 向前端返回消息页面
     * @param pageNum
     * @param pageSize
     * @return List<Message>
     */
    @Override
    public List<Message> getMessagePage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Message> messages = messageMapper.getAllMessages();
        return messages;
    }

    /**
     * 通过id查找留言信息
     * @param id
     * @return message
     */
    @Override
    public Message getMessageById(Integer id) {
        return messageMapper.getMessageById(id);
    }

    /**
     * 向message表中添加一条数据
     * @param message
     * @return int
     */
    @Override
    public int addMessage(Message message) {
        message.setCreateDate(new Timestamp(new Date().getTime()));
        return messageMapper.addMessage(message);
    }

    /**
     * 更新message表中的赞数，同时向Likes表中插入记录
     * @param message
     * @Param uid
     * @return
     */
    @Override
    public void addLike(Message message, Integer uid) {
        messageMapper.changeLike(message);
        Likes likes = new Likes();
        likes.setMessage_id(message.getId());
        likes.setUser_id(uid);
        likesMapper.addLike(likes);
    }

    /**
     * 更新message表中的赞数，同时删除Likes表中的记录
     * @param message
     * @Param uid
     * @return
     */
    @Override
    public void addDislike(Message message, Integer uid){
        messageMapper.changeLike(message);
        Likes likes = new Likes();
        likes.setMessage_id(message.getId());
        likes.setUser_id(uid);
        likesMapper.addDislike(likes);
    }

    /**
     * 判断当前赞是否存在
     * @param likes
     * @return
     */
    @Override
    public boolean hasLike(Likes likes){
        if(likesMapper.hasLike(likes).isEmpty())
            return false;
        return true;
    }

}
