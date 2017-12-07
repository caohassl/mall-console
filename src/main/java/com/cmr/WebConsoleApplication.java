package com.cmr;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Created by Administrator on 2017/12/4.
 */
@Slf4j
@SpringBootApplication//等同于 @Configuration @EnableAutoConfiguration @ComponentScanpublic
@MapperScan("com.cmr.dao")
public class WebConsoleApplication {

    public static void main(String args[]){
        log.info("webConsole Application is starting ...");
        SpringApplication.run(WebConsoleApplication.class,args);
    }



}
