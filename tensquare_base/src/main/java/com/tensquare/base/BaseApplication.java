package com.tensquare.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/2/9
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 **/
@SpringBootApplication
public class BaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class,args);
    }

    /**
     * 分布式ID生成器
     * @return
     */
    @Bean
    public IdWorker idWorker(){
        return  new IdWorker();
    }
}
