package com.b328.messageboard.controller;

import com.b328.messageboard.entity.Likes;
import com.b328.messageboard.entity.Message;
import com.b328.messageboard.entity.vo.LikeInfoVo;
import com.b328.messageboard.entity.vo.MessagePageVo;
import com.b328.messageboard.mapper.UserMapper;
import com.b328.messageboard.service.MessageService;
import com.b328.messageboard.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
public class MessageController {
    @Autowired
    @Qualifier("MessageService1")
    MessageService messageService;
    @Autowired
    UserService userService;

    /**
     *
     * @param messagePageVo
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/getMessagePage", method = RequestMethod.POST)
    @ResponseBody
    public PageInfo<Message> getMessagePage(@RequestBody MessagePageVo messagePageVo) {
        List<Message> messages = messageService.getMessagePage(messagePageVo.getPageNum(), messagePageVo.getPageSize());
        PageInfo<Message> pageInfo = new PageInfo<>(messages);
        return pageInfo;
    }

    /**
     * 通过id检索留言信息
     * @param id
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/getMessage/{id}", method = RequestMethod.GET)
    public Message getMessageById(@PathVariable(name = "id") int id) {
        return messageService.getMessageById(id);
    }

    /**
     * 添加一个留言
     * @param message
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/addMessage", method = RequestMethod.POST)
    @ResponseBody
    public int addMessage(@RequestBody Message message) {
        if (message.getContent().equals("") || message.getTitle().equals(""))
            return 0;
        return messageService.addMessage(message);
    }

    /**
     * 为当前留言添加一个赞
     * @param likeInfoVo
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/addLike/{id}", method = RequestMethod.POST)
    @ResponseBody
    public void addLike(/*@PathVariable(name = "id") int id*/@Valid @RequestBody LikeInfoVo likeInfoVo) {
        Integer uid = userService.getIdByName(likeInfoVo.getUser_name());
        Likes likes = new Likes();
        likes.setMessage_id(likeInfoVo.getMessage_id());
        likes.setUser_id(uid);
        if(hasLike(likes)) {
            Message message = messageService.getMessageById(likeInfoVo.getMessage_id());
            message.setLike_number(message.getLike_number() + 1);
            messageService.addLike(message,uid);
        }
    }

    /**
     * 为当前留言取消一个赞
     * @param likeInfoVo
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/addDislike/{id}", method = RequestMethod.POST)
    @ResponseBody
    public void addDislike(/*@PathVariable(name = "id") int id*/@Valid @RequestBody LikeInfoVo likeInfoVo) {
        Integer uid = userService.getIdByName(likeInfoVo.getUser_name());
        Likes likes = new Likes();
        likes.setMessage_id(likeInfoVo.getMessage_id());
        likes.setUser_id(uid);
        if(hasLike(likes)) {
            Message message = messageService.getMessageById(likeInfoVo.getMessage_id());
            message.setLike_number(message.getLike_number() - 1);
            messageService.addDislike(message,uid);
        }
    }

    /**
     * 判断是否存在此点赞信息
     * @param likes
     * @return
     */
    public boolean hasLike(Likes likes){
        return messageService.hasLike(likes);
    }


//    /**
//     * 为当前留言添加一个赞
//     * @param id
//     * @return
//     */
//    @CrossOrigin
//    @RequestMapping(value = "/addLike/{id}", method = RequestMethod.POST)
//    @ResponseBody
//    public void addLike(@PathVariable(name = "id") int id) {
//
//        Message message = messageService.getMessageById(id);
//        message.setLike_number(message.getLike_number() + 1);
//        messageService.addLike(message, 14);
//
//    }
//
//    /**
//     * 为当前留言取消一个赞
//     * @param id
//     * @return
//     */
//    @CrossOrigin
//    @RequestMapping(value = "/addDislike/{id}", method = RequestMethod.POST)
//    @ResponseBody
//    public void addDislike(@PathVariable(name = "id") int id) {
//        Message message = messageService.getMessageById(id);
//        message.setLike_number(message.getLike_number() - 1);
//        messageService.addDislike(message, 14);
//    }
}
