package com.cmr;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Administrator on 2017/12/4.
 */
@Slf4j
@SpringBootApplication
@EnableAutoConfiguration
public class WebConsoleApplication {

    public static void main(String args[]){
        log.info("webConsole Application is starting ...");
        SpringApplication.run(WebConsoleApplication.class,args);
    }
}
