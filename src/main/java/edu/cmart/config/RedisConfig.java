//package edu.cmart.config;
//
//import edu.cmart.model.dto.LocationDriver;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//
//@Configuration
//public class RedisConfig {
//
//    @Bean
//    public RedisTemplate<String, LocationDriver> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        RedisTemplate<String, LocationDriver> template = new RedisTemplate<>();
//        template.setConnectionFactory(redisConnectionFactory);
//        return template;
//    }
//}
