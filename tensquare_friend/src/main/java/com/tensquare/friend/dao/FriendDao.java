package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.lang.reflect.Field;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/2/26
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 **/

public interface FriendDao extends JpaRepository<Friend,String> {

    public Friend findByAndUseridAndFriendid(String userid,String friendid);

    /*** 更新为互相喜欢 * @param userid * @param friendid */
    @Modifying
    @Query("update Friend f set f.islike=?3 where f.userid=?1 and f.friendid=?2")
    public void updateLike(String userid,String friendid,String islike);

    /*** 更新为互相喜欢 * @param userid * @param friendid */
    @Modifying
    @Query("delete from Friend f where f.userid=?1 and f.friendid=?2")
    public void deleteByUseridAndFriendid(String userid,String friendid);


}
