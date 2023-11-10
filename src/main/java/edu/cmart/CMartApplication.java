package edu.cmart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CMartApplication {

    public static void main(String[] args){

        SpringApplication.run(CMartApplication.class, args);
    }
}
