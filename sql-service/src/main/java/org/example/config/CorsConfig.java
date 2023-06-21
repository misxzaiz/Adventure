package org.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 设置允许跨域请求的路径
        registry.addMapping("/**")
                // 设置允许跨域请求的源
                .allowedOriginPatterns("*")
                // 设置允许请求的方法
                .allowedMethods("GET", "POST", "PUT", "DELETE","OPTION")
                // 是否允许携带 Cookie
                .allowCredentials(true)
                // 允许缓存的秒数
                .maxAge(3600);
    }
}
