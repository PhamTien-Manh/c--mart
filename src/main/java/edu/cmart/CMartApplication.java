package edu.cmart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class CMartApplication {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        SpringApplication.run(CMartApplication.class, args);
    }

}
