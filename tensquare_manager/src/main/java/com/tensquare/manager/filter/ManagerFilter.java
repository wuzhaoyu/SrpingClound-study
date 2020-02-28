package com.tensquare.manager.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * 类功能说明: 网关过滤器
 * 类修改者	创建日期2020/2/27
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 **/
@Component
public class ManagerFilter extends ZuulFilter {

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * pre ：可以在请求被路由之前调用
     * route ：在路由请求时候被调用
     * post ：在route和error过滤器之后被调用
     * error ：处理请求时发生错误时被调用
     *
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 每个过滤器的比较级 数字从小到大，级别逐渐减小
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * 返回一个boolean类型来判断该过滤器是否要执行，
     * 所以通过此函数可 实现过滤器的开关。在上例中，
     * 我们直接返回true，所以该过滤器总是生效
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 执行的路径
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        System.out.println("过滤器成功");
        //获取当前上下文
        RequestContext currentContext = RequestContext.getCurrentContext();
        //获取请求对象
        HttpServletRequest request = currentContext.getRequest();
        String authorization = request.getHeader("Authorization");

        //预请求 Preflighted Request在发送真正的请求前，
        // 会先发送一个方法为OPTIONS的预请求(Preflighted Request)，
        // 用于试探服务端是否能接受真正的请求。如果options获得的回应时拒绝性质的，
        // 如404、403、500等状态，就会停止post、get请求的发出
        if (request.getMethod().equals("OPTIONS")) {
            return null;
        }
        //登录的url放行
        if (request.getRequestURL().indexOf("login") > 0) {
            //todo 调整登录页面
            return null;
        }
        if (authorization != null && !"".equals(authorization)) {
            if (authorization.startsWith("Bearer ")) {
                try {
                    String token = authorization.substring(7);
                    Claims claims = jwtUtil.parseJWT(token);
                    String roles = (String) claims.get("roles");
                    if (roles.equals("admin")) {
                        //重新设置
                        currentContext.addZuulRequestHeader("Authorization", authorization);
                        System.out.println("token 验证通过，添加了头信息"+authorization);
                        return null;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    //禁止
                    currentContext.setSendZuulResponse(false);
                }

            }
        }
        currentContext.setResponseStatusCode(403);
        currentContext.setResponseBody("暂无权限");
        currentContext.getResponse().setContentType("text/html;charset=UTF-8");
        currentContext.setSendZuulResponse(false);
        return null;
    }
}
