package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.Friend;
import com.tensquare.friend.pojo.NoFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/2/26
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 **/

public interface NoFriendDao extends JpaRepository<NoFriend,String> {

     NoFriend findByAndUseridAndFriendid(String userid, String friendid);

}
