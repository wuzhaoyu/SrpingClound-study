package com.tensquare.friend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/2/26
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 **/
@FeignClient(value = "tensquare-user")
public interface UserClient {

    /**
     * 注意：1. @PathVariable("userid") 中的userid必须指定
     *       2. 路径要为完整路径"/user/incFansFollowCount/{userid}/{friendid}/{x}"
     * @param userid
     * @param friendid
     * @param x
     */
    @RequestMapping(value = "/user/incFansFollowCount/{userid}/{friendid}/{x}", method = RequestMethod.POST)
    public void incFansFollowCount(@PathVariable("userid") String userid, @PathVariable("friendid") String friendid, @PathVariable int x);
}
