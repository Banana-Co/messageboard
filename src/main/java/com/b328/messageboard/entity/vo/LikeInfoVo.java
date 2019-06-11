package com.b328.messageboard.entity.vo;

public class LikeInfoVo {
    /**
     * 用户名
     */
    String user_name;
    /**
     * 留言号
     */
    Integer message_id;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Integer getMessage_id() {
        return message_id;
    }

    public void setMessage_id(Integer message_id) {
        this.message_id = message_id;
    }
}
