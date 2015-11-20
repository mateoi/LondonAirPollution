package com.comp3001.team3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * Created by Anthony on 9/10/2015.
 */
@SpringBootApplication
public class No2Pollution  extends SpringBootServletInitializer {

    //@Override
    // protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    //     return application.sources(No2Pollution.class);
    // }


    public static void main(String[] args) {
        SpringApplication.run(No2Pollution.class, args);
    }

}