package com.shining3d.zeus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by fe on 2016/12/8.
 */
@EnableAutoConfiguration
public class Zeus {
    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(Zeus.class);
        app.setWebEnvironment(true);
        Set<Object> set = new HashSet<Object>();
        set.add("classpath:spring.xml");
        app.setSources(set);
        app.run(args);


    }

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Zeus.class);
    }
}
