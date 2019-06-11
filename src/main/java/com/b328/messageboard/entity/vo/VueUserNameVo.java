package com.b328.messageboard.entity.vo;

import javax.validation.constraints.NotNull;

public class VueUserNameVo {
    /**
     * 用户名
     */
    @NotNull(message="用户名不允许为空")
    private String username;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
