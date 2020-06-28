package com.tensquare.qa.client.impl;

import com.tensquare.qa.client.BaseClient;
import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Component;

/**
 * 类功能说明: 服务调用的回调实现 触发熔断器
 * 类修改者	创建日期2020/2/28
 * 修改说明
 * @author wzy
 * @version V1.0
 **/
@Component
public class BaseClientImpl implements BaseClient {
    @Override
    public Result findById(String id) {
        return new Result(false, StatusCode.ERROR,"熔断器触发了,tensquare-base服务降级");
    }
}
