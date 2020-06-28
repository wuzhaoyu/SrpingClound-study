package com.tensquare.web.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 类功能说明: 解决由于网关转发导致的request header数据丢失问题
 * 类修改者	创建日期2020/2/27
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 **/
@Component
public class WebFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //获取当前上下文
        RequestContext currentContext = RequestContext.getCurrentContext();
        //获取请求对象
        HttpServletRequest request = currentContext.getRequest();
        String authorization = request.getHeader("Authorization");
        //重新设置 判断是否有头信息
        if (authorization != null && !"".equals(authorization)) {
            // 将头信息继续传递
            currentContext.addZuulRequestHeader("Authorization", authorization);
        }
        return null;
    }
}
