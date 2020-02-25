package com.tensquare.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/2/23
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 **/
@SpringBootApplication
public class SearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class);
    }

    @Bean
    public IdWorker getIdWork(){
        return new IdWorker();
    }
}
