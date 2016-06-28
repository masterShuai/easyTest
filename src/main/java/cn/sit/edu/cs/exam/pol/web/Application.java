package cn.sit.edu.cs.exam.pol.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * supershank mobile
 * Created by Administrator on 2015/4/4.
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
@EnableCaching
public class Application {

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(Application.class, args);

    }

}
