package com.b328.messageboard.controller;

import com.b328.messageboard.entity.Message;
import com.b328.messageboard.entity.vo.MessagePageVo;
import com.b328.messageboard.service.MessageService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class MessageController {
    @Autowired
    @Qualifier("MessageService1")
    MessageService messageService;
    @CrossOrigin
    @RequestMapping(value = "/getMessagePage", method = RequestMethod.POST)
    @ResponseBody
    public PageInfo<Message> getMessagePage(@RequestBody MessagePageVo messagePageVo) {
        List<Message> messages = messageService.getMessagePage(messagePageVo.getPageNum(), messagePageVo.getPageSize());
        PageInfo<Message> pageInfo = new PageInfo<>(messages);
        return pageInfo;
    }
    @CrossOrigin
    @RequestMapping(value = "/getMessage/{id}", method = RequestMethod.GET)
    public Message getMessageById(@PathVariable(name = "id") int id) {
        return messageService.getMessageById(id);
    }

    @CrossOrigin
    @RequestMapping(value = "/addMessage", method = RequestMethod.POST)
    @ResponseBody
    public int addMessage(@RequestBody Message message) {
        if (message.getContent().equals("") || message.getTitle().equals(""))
            return 0;
        return messageService.addMessage(message);
    }

    @CrossOrigin
    @RequestMapping(value = "/addLike/{id}", method = RequestMethod.POST)
    @ResponseBody
    public void addLike(@PathVariable(name = "id") int id) {
        Message message=messageService.getMessageById(id);
        message.setLike_number(message.getLike_number()+1);
        messageService.changeLike(message);
    }

    @CrossOrigin
    @RequestMapping(value = "/addDislike/{id}", method = RequestMethod.POST)
    @ResponseBody
    public void addDislike(@PathVariable(name = "id") int id) {
        Message message=messageService.getMessageById(id);
        message.setLike_number(message.getLike_number()-1);
        messageService.changeLike(message);
    }

}
