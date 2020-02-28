package com.tensquare.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/2/27
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 **/
@SpringBootApplication
@EnableConfigServer //开启配置服务
public class ConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication.class);
    }
}
