package com.b328.messageboard.controller;

import com.b328.messageboard.entity.Likes;
import com.b328.messageboard.entity.Message;
import com.b328.messageboard.entity.vo.LikeInfoVo;
import com.b328.messageboard.entity.vo.MessagePageVo;
import com.b328.messageboard.result.Result;
import com.b328.messageboard.result.ResultCode;
import com.b328.messageboard.result.ResultFactory;
import com.b328.messageboard.service.IMessageService;
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
    IMessageService IMessageService;
    @Autowired
    UserService userService;

    /**
     *向前端返回消息页面
     * @param messagePageVo
     * @return PageInfo<Message>
     */
    @CrossOrigin
    @RequestMapping(value = "/getMessagePage", method = RequestMethod.POST)
    @ResponseBody
    public PageInfo<Message> getMessagePage(@RequestBody MessagePageVo messagePageVo) {
        List<Message> messages = IMessageService.getMessagePage(messagePageVo.getPageNum(), messagePageVo.getPageSize());
        PageInfo<Message> pageInfo = new PageInfo<>(messages);
        return pageInfo;
    }

    /**
     * 通过id检索留言信息
     * @param id
     * @return Message
     */
    @CrossOrigin
    @RequestMapping(value = "/getMessage/{id}", method = RequestMethod.GET)
    public Message getMessageById(@PathVariable(name = "id") int id) {
        return IMessageService.getMessageById(id);
    }

    /**
     * 添加一个留言
     * @param message
     * @return int
     */
    @CrossOrigin
    @RequestMapping(value = "/addMessage", method = RequestMethod.POST)
    @ResponseBody
    public int addMessage(@RequestBody Message message) {
        if (message.getContent().equals("") || message.getTitle().equals(""))
            return 0;
        return IMessageService.addMessage(message);
    }

    /**
     * 为当前留言添加一个赞
     * @param likeInfoVo
     * @return Result
     */
    @CrossOrigin
    @RequestMapping(value = "/addLike", method = RequestMethod.POST)
    @ResponseBody
    public Result addLike(/*@PathVariable(name = "id") int id*/@Valid @RequestBody LikeInfoVo likeInfoVo) {
        Integer uid = userService.getIdByName(likeInfoVo.getUser_name());
        Likes likes = new Likes();
        likes.setMessage_id(likeInfoVo.getMessage_id());
        likes.setUser_id(uid);
        if(!hasLike(likes)) {
            Message message = IMessageService.getMessageById(likeInfoVo.getMessage_id());
            message.setLike_number(message.getLike_number() + 1);
            IMessageService.addLike(message,uid);
            return ResultFactory.buildSuccessResult("成功");
        }else {
            return ResultFactory.buildFailResult(ResultCode.FAIL);
        }
    }

    /**
     * 为当前留言取消一个赞
     * @param likeInfoVo
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
            Message message = IMessageService.getMessageById(likeInfoVo.getMessage_id());
            message.setLike_number(message.getLike_number() - 1);
            IMessageService.addDislike(message,uid);
        }
    }

    /**
     * 判断是否存在此点赞信息
     * @param likes
     * @return boolean
     */
    public boolean hasLike(Likes likes){
        return IMessageService.hasLike(likes);
    }

}
