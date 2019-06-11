package com.b328.messageboard.controller;

import com.b328.messageboard.entity.vo.VueUserNameVo;
import com.b328.messageboard.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class TestController {
    @Autowired
    private IUserService userService;

    /**
     *向前端返回注册时间
     * @param vueLoginInfoVo
     * @return String
     */
    @CrossOrigin
    @RequestMapping(value = "/time", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public String getTime(@Valid @RequestBody VueUserNameVo vueLoginInfoVo) {
        return userService.getUser(vueLoginInfoVo.getUsername()).getRegister_time().toString();
    }

    /**
     * 测试返回注册时间
     * @param username
     * @return
     */
    @RequestMapping(value = "/testtime", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public String getTestTime(@RequestParam(value="username") String username) {
        return userService.getUser(username).getRegister_time().toString();
    }
}
