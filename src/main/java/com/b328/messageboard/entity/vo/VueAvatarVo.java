package com.b328.messageboard.entity.vo;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

public class VueAvatarVo {
    /**
     * 用户名
     */
    @NotNull(message="用户名不允许为空")
    private String username;

    /**
     * 头像
     */
    @NotNull(message = "图片为空")
    MultipartFile avatar;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }
}
