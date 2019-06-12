package com.b328.messageboard.controller;

import com.b328.messageboard.entity.User;
import com.b328.messageboard.entity.vo.VueChangPswdVo;
import com.b328.messageboard.entity.vo.VueLoginInfoVo;
import com.b328.messageboard.result.Result;
import com.b328.messageboard.result.ResultCode;
import com.b328.messageboard.result.ResultFactory;
import com.b328.messageboard.service.IUserService;
import com.b328.messageboard.util.Md5SaltTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private IUserService userService;

    /**
     * 登录
     * @param loginInfoVo
     * @param bindingResult
     * @return Result
     */
    @CrossOrigin
    @RequestMapping(value = "login", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public Result login(@Valid @RequestBody VueLoginInfoVo loginInfoVo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String message = String.format("登陆失败，详细信息[%s]。", bindingResult.getFieldError().getDefaultMessage());
            return ResultFactory.buildFailResult(message);
        }
        User user=userService.getUser(loginInfoVo.getUsername());

        try {
            if (user==null){
                return ResultFactory.buildFailResult(ResultCode.NotExist);
            }else if (!Md5SaltTool.validPassword(loginInfoVo.getPassword(),user.getUser_password())){
                return ResultFactory.buildFailResult(ResultCode.FAIL);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return ResultFactory.buildSuccessResult("登陆成功。");
    }

    /**
     * 注册
     * @param loginInfoVo
     * @param bindingResult
     * @return Result
     */
    @CrossOrigin
    @RequestMapping(value = "register", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public Result register(@Valid @RequestBody VueLoginInfoVo loginInfoVo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String message = String.format("注册失败，详细信息[%s]。", bindingResult.getFieldError().getDefaultMessage());
            return ResultFactory.buildFailResult(message);
        }
        User user=userService.getUser(loginInfoVo.getUsername());
        if (user!=null){
            return ResultFactory.buildFailResult(ResultCode.HaveExist);
        }else if (loginInfoVo.getUsername().equals("")||loginInfoVo.getPassword().equals("")){
            return ResultFactory.buildFailResult(ResultCode.FAIL);
        }
        if(!rexCheckPassword(loginInfoVo.getPassword()))
            return ResultFactory.buildFailResult(ResultCode.INVALID_PASSWORD);
        User user1=new User();
        String encryptedPwd = null;
        try {
            encryptedPwd = Md5SaltTool.getEncryptedPwd(loginInfoVo.getPassword());
            user1.setUser_name(loginInfoVo.getUsername());
            user1.setUser_password(encryptedPwd);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        userService.addUser(user1);
        return ResultFactory.buildSuccessResult("注册成功。");
    }

    /**
     * 正则表达式验证密码
     * @param input
     * @return
     */
    public static boolean rexCheckPassword(String input) {
        // 6-20 位，字母、数字、字符
        String regStr = "^([A-Z]|[a-z]|[0-9]|[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）――+|{}【】‘；：”“'。，、？]){6,20}$";
        return input.matches(regStr);
    }

    /**
     * 修改密码
     * @param changPswdVo
     * @return Result
     */
    @CrossOrigin
    @RequestMapping(value = "/changepswd", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public Result changePassword(@Valid @RequestBody VueChangPswdVo changPswdVo){
        User user = userService.getUser(changPswdVo.getUsername());
        String encryptedPwd = null;
        try {
            if (!Md5SaltTool.validPassword(changPswdVo.getOld_password(),user.getUser_password())){
                return ResultFactory.buildFailResult(ResultCode.FAIL);
            }
            if(!rexCheckPassword(changPswdVo.getNew_password()))
                return ResultFactory.buildFailResult(ResultCode.INVALID_PASSWORD);
            encryptedPwd = Md5SaltTool.getEncryptedPwd(changPswdVo.getNew_password());
            user.setUser_password(encryptedPwd);
            userService.changePswd(user);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return ResultFactory.buildSuccessResult("修改密码成功。");
    }

    /**
     * 测试注册
     * @param username
     * @param password
     * @return Result
     */
    @CrossOrigin
    @RequestMapping(value = "testregister", method = RequestMethod.POST)
    public Result testregister(@RequestParam(value="username") String username, @RequestParam(value="password") String password) {
        User user=userService.getUser(username);
        if (user!=null){
            return ResultFactory.buildFailResult(ResultCode.HaveExist);
        }else if (username.equals("")||password.equals("")){
            return ResultFactory.buildFailResult(ResultCode.FAIL);
        }
        User user1=new User();
        user1.setUser_name(username);
        user1.setUser_password(password);
        userService.addUser(user1);
        return ResultFactory.buildSuccessResult("注册成功。");
    }

    /**
     * 测试修改密码
     * @param username
     * @param old_password
     * @param new_password
     * @return Result
     */
    @CrossOrigin
    @RequestMapping(value = "/cp", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public Result testChangePswd(@RequestParam(value="username") String username, @RequestParam(value="old_password") String old_password, @RequestParam(value="new_password") String new_password) {
        User user = userService.getUser(username);
        String encryptedPwd = null;
        try {
            if (!Md5SaltTool.validPassword(old_password,user.getUser_password())){
                return ResultFactory.buildFailResult(ResultCode.FAIL);
            }
            encryptedPwd = Md5SaltTool.getEncryptedPwd(new_password);

            user.setUser_password(encryptedPwd);
            userService.changePswd(user);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return ResultFactory.buildSuccessResult("修改密码成功。");
    }

    /**
     * 返回所有用户
     * @return List<User>
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * 添加用户
     * @param user
     * @return int
     */
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public int addUser(@RequestBody User user) {
        return userService.addUser(user);
    }
}
