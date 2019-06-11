package com.b328.messageboard.entity;

import java.sql.Timestamp;

public class User {
    /**
     * 用户号
     */
    private Integer user_id;
    /**
     * 用户名
     */
    private String user_name;
    /**
     * 用户密码
     */
    private String user_password;
    /**
     * 用户注册时间
     */
    private Timestamp register_time;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public Timestamp getRegister_time() {
        return register_time;
    }

    public void setRegister_time(Timestamp register_time) {
        this.register_time = register_time;
    }
}
