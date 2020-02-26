package com.tensquare.friend.service;

import com.tensquare.friend.client.UserClient;
import com.tensquare.friend.dao.FriendDao;
import com.tensquare.friend.dao.NoFriendDao;
import com.tensquare.friend.pojo.Friend;
import com.tensquare.friend.pojo.NoFriend;
import common.Contant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/2/26
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 **/
@Service
@Transactional
public class FriendService {

    @Autowired
    private FriendDao dao;


    @Autowired
    private NoFriendDao noFriendDao;

    @Autowired
    private UserClient userClient;

    /**
     * 交友
     *
     * @param userid   当前用户
     * @param friendid 朋友ID
     * @return
     */
    public int addFriend(String userid, String friendid) {
        //是否建立好友状态
        Friend friend = dao.findByAndUseridAndFriendid(userid, friendid);
        //未建立好友状态
        if (friend != null) {
            return 0;
        }
        // 创建好友状态
        Friend save = new Friend();
        save.setUserid(userid);
        save.setFriendid(friendid);
        save.setIslike("0");
        dao.save(save);
        //如果存在对方喜欢
        //判断对方是否喜欢你，如果喜欢，将islike设置为1
         if(dao.findByAndUseridAndFriendid( friendid,userid) != null){
             dao.updateLike(userid,friendid,"1");
             dao.updateLike(friendid,userid,"1"); }
         return 1;
    }

    /**
     * 向不喜欢列表中添加记录
     * @param userid
     * @param friendid
     */
    public int addNoFriend(String userid,String friendid){
        NoFriend byAndUseridAndFriendid = noFriendDao.findByAndUseridAndFriendid(userid, friendid);
       if(byAndUseridAndFriendid !=null){
           return 0;
       }
        NoFriend noFriend=new NoFriend();
        noFriend.setUserid(userid);
        noFriend.setFriendid(friendid);
        noFriendDao.save(noFriend);
        return 1;
    }

    /**
     * 删除好友
     * @param userid
     * @param friendid
     * @return
     */
    public int deleteFriend(String userid,String friendid){
        //好友关系数据删除
        dao.deleteByUseridAndFriendid(userid,friendid);
        // 朋友对自己的关系islike 更新为0
        dao.updateLike(Contant.ZERO_STR,friendid,userid);
        //不是朋友
        NoFriend noFriend = new NoFriend();
        noFriend.setUserid(userid);
        noFriend.setFriendid(friendid);
        noFriendDao.save(noFriend);
        return 1;
    }
}
