package edu.cmart;

import edu.cmart.util.method.Convert;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.text.Document;
import java.text.Normalizer;

@SpringBootApplication
public class CMartApplication {
    public static void main(String[] args){
        SpringApplication.run(CMartApplication.class, args);
    }
}
