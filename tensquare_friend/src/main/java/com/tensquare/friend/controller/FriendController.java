package com.tensquare.friend.controller;

import com.tensquare.friend.client.UserClient;
import com.tensquare.friend.service.FriendService;
import common.Contant;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/2/26
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 **/
@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private FriendService friendService;

   @Autowired
   private UserClient userClient;

    /**
     * 添加好友
     *
     * @param friendid 对方用户ID
     * @param type     1：喜欢 0：不喜欢
     * @return
     */
    @RequestMapping(value = "/like/{friendid}/{type}", method = RequestMethod.PUT)
    public Result addFriend(@PathVariable String friendid, @PathVariable String type) {
        //判断是不是登录
        Claims claims = (Claims) request.getAttribute("user_claims");
        if (claims == null) {
            return new Result(false, StatusCode.ACCESSERROR, "无权访问");
        }
        //如果是喜欢
        if(type == null){
            return new Result(true, StatusCode.ERROR, "参数错误");
        }
        if (type.equals(Contant.ONE_STR)) {
            if (friendService.addFriend(claims.getId(), friendid) == 0) {
                return new Result(false, StatusCode.REPERROR, "已经添加此好友");
            }
            // 好友的粉丝数增加 增加用户自己关注数增加
            userClient.incFansFollowCount(claims.getId(),friendid,1);
        } else {
            if(friendService.addNoFriend(claims.getId(), friendid) == 0){
                return new Result(false, StatusCode.REPERROR, "已经为非好友");
            }
        }
        return new Result(true, StatusCode.OK, "操作成功");
    }

    /**
     * 删除好友
     * @param friendid 对方用户ID
     * @return
     */
    @RequestMapping(value = "/delete/{friendid}", method = RequestMethod.DELETE)
    public Result deleteFriend(@PathVariable String friendid) {
        //判断是不是登录
        Claims claims = (Claims) request.getAttribute("user_claims");
        if (claims == null) {
            return new Result(false, StatusCode.ACCESSERROR, "无权访问");
        }

        friendService.deleteFriend(claims.getId(),friendid);
        //粉丝数与关注数 -1 删除好友 个人的粉丝数减一，好友的关注数减一
        userClient.incFansFollowCount(claims.getId(),friendid,-1);
        return new Result(true, StatusCode.OK, "操作成功");
    }
}
