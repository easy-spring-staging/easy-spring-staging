package com.easyspring.demo.mvc.simple;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class AppLaunch {

    public static void main( String[] args ){
        log.info("-----------------------服务启动开始-----------------------");
        SpringApplication.run(AppLaunch.class, args);
        log.info("-----------------------服务启动结束-----------------------");

    }

}
