package com.b328.messageboard.entity;

import com.b328.messageboard.entity.vo.LikeInfoVo;

public class Likes {
    private Integer user_id;
    private Integer message_id;

    /**
     * 将LikeInfoVo类转为Likes类
     * @param likeInfoVo
     * @return
     */
    public void Vo2Likes(LikeInfoVo likeInfoVo){
        this.message_id = likeInfoVo.getMessage_id();
        this.user_id = likeInfoVo.getUser_id();
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getMessage_id() {
        return message_id;
    }

    public void setMessage_id(Integer message_id) {
        this.message_id = message_id;
    }
}
