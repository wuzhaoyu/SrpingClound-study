package com.tensquare.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/2/25
 * 修改说明
 * 我们在添加了spring security依赖后，所有的地址都被spring security所控制了，
 * 我们目 前只是需要用到BCrypt密码加密的部分，所以我们要添加一个配置类，
 *  配置为所有地址 都可以匿名访问。
 *
 * @author wzy
 * @version V1.0
 **/
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //authorizeRequests 权限认知所有请求
        //antMatchers拦截过滤路径的规则
        //apermitAll 路径的权限 这边是不要任何权限
        // anyRequest 任何请求
        // authenticated 认证后才能访问
        // .and().csrf().disable(); // csrf攻击，security外部访问的连接被认为为外部攻击
        // 这边将csrf功能去除
        http.
                authorizeRequests()
                .antMatchers("/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }
}
