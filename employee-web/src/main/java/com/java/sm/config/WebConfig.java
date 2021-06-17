package com.java.sm.config;

import com.java.sm.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 722A-08-CXB
 * @version 1.0.0
 * @ClassName WebConfig.java
 * @CreateTime 2021年06月14日 21:03:00
 */
@Configuration
@ComponentScan(basePackages = {"com.java.sm.controller","com.java.sm.exception"})
@EnableWebMvc //配置注解驱动

public class WebConfig implements WebMvcConfigurer {
    //等价于配置文件中 <mvc:cors/> 解决跨域问题 （在filter中配置之后可以删除）
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowedHeaders("*")  //允许所有请求头
//                .allowedMethods("*")  //允许所有方法
//                .allowCredentials(true); //添加允许携带cookie
//    }

    //配置拦截器
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginInterceptor())
//                .addPathPatterns("/**") //需要拦截的请求，\**表示所有请求
//                .excludePathPatterns("/common/**");    //表示需要排除的请求
//    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        return jedisConnectionFactory;
    }

//    @Bean
//    public RedisTemplate redisTemplate(){
//        RedisTemplate redisTemplate = new RedisTemplate();
//        redisTemplate.setConnectionFactory(redisConnectionFactory());
//        return  redisTemplate;
//    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(){
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(redisConnectionFactory());
        return stringRedisTemplate;
    }
}
