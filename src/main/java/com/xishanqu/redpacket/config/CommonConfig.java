package com.xishanqu.redpacket.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @Author BaoNing 2019/7/3
 */
@Configuration
public class CommonConfig {

    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        // 允许跨域访问的域名
        config.addAllowedOrigin("*");
        // 请求头
        config.addAllowedHeader("*");
        // 请求方法
        config.addAllowedMethod("*");
        // 预检请求的有效期，单位为秒。
        config.setMaxAge(3600L);
        // 是否支持安全证书
        config.setAllowCredentials(true);
        config.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }

}
