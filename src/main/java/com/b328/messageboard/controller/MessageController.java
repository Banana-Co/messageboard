package com.b328.messageboard.controller;

import com.b328.messageboard.entity.Message;
import com.b328.messageboard.entity.vo.MessagePageVo;
import com.b328.messageboard.serivce.MessageService;
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

    @RequestMapping(value = "/api/getMessagePage", method = RequestMethod.POST)
    @ResponseBody
    public PageInfo<Message> getMessagePage(@RequestBody MessagePageVo messagePageVo) {
        List<Message> messages = messageService.getMessagePage(messagePageVo.getPageNum(), messagePageVo.getPageSize());
        PageInfo<Message> pageInfo = new PageInfo<>(messages);
        return pageInfo;
    }

    @RequestMapping(value = "/api/getMessage/{id}", method = RequestMethod.GET)
    public Message getMessageById(@PathVariable(name = "id") int id) {
        return messageService.getMessageById(id);
    }

    @RequestMapping(value = "/api/addMessage", method = RequestMethod.POST)
    @ResponseBody
    public int addMessage(@RequestBody Message message) {
        if (message.getContent().equals("") || message.getTitle().equals(""))
            return 0;
        return messageService.addMessage(message);
    }
}
