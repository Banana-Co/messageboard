package com.b328.messageboard.service;

import com.b328.messageboard.entity.Likes;
import com.b328.messageboard.entity.Message;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IMessageService {
    /**
     * 返回留言页
     * @param pageNum
     * @param pageSize
     * @return List<Message>
     */
    List<Message> getMessagePage(Integer pageNum, Integer pageSize);

    /**
     * 通过id查找留言
     * @param id
     * @return Message
     */
    Message getMessageById(Integer id);

    /**
     * 添加留言
     * @param message
     * @return
     */
    int addMessage(Message message);

    /**
     * 添加赞
     * @param message
     * @param uid
     */
    void addLike(Message message, Integer uid);

    /**
     * 取消赞
     * @param message
     * @param uid
     */
    void addDislike(Message message, Integer uid);

    /**
     * 判断是否有赞
     * @param likes
     * @return
     */
    boolean hasLike(Likes likes);
}
