package com.tensquare.qa.client;

import entity.Result;
import org.springframework.cloud.openfeign.EnableFeignClients;
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
@FeignClient(value = "tensquare-base")
public interface BaseClient {

    /*** 根据ID查询标签 * @param id * @return */
    @RequestMapping(value = "/label/{id}", method = RequestMethod.GET)
     Result findById(@PathVariable("id") String id) ;
}
