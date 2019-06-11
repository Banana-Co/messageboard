package com.b328.messageboard.mapper;

import com.b328.messageboard.entity.Likes;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LikesMapper {
    /**
     * 添加赞
     * @param likes
     */
    @Insert("INSERT INTO Likes(uid, mid) VALUES(#{user_id}, #{message_id})")
    void addLike(Likes likes);

    /**
     * 取消赞
     * @param likes
     */
    @Delete("DELETE FROM Likes WHERE uid = #{user_id} AND MID = #{message_id}")
    void addDislike(Likes likes);

    /**
     * 判断是否赞过
     * @param likes
     * @return List<Likes>
     */
    @Select("SELECT * FROM Likes WHERE uid = #{user_id} AND mid = #{message_id}")
    List<Likes> hasLike(Likes likes);
}
