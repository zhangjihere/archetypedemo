package com.samsung.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 跨域访问的全局配置，再次配置即可以不必配置相同的Controller的@CrossOrigin主机{@link com.samsung.demo.web.HelloController}
 * Created by ji.zhang on 7/8/17.
 */
@Configuration
//@EnableWebMvc 1 增加这个注解会使的docs端点无法打开。猜测可能是重复开启WebMvc而docs端点比较特殊，这个注解不加也会被autoconfig的
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://www.xxx.com", "http://www.yyy.com")
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }

}
