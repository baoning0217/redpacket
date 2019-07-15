package com.xishanqu.redpacket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author BaoNing 2019/7/15
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer   {

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xishanqu.redpacket.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    /**
     * 设置API基础信息
     */
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .description("RedPacket接口测试文档")
                .contact(contact())
                .version("v1.0")
                .title("API测试文档")
                .termsOfServiceUrl("http://www.xishanqu.com")
                .build();
    }

    /**
     * 设置文档联系方式
     */
    private Contact contact(){
        return new Contact("captain","https://github.com/baoning0217", "bnwyyx217@163.com");
    }

    /**
     * 配置拦截器
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/templates/**")
                .addResourceLocations("classpath:/META-INF/resources/templates/");
    }


//    @Override
//    public void configure(WebSecurity webSecurity) throws Exception {
//        webSecurity.ignoring().antMatchers("/v2/api-docs",
//                "/configuration/ui",
//                "/swagger-resources",
//                "/configuration/security",
//                "/swagger-ui.html",
//                "/webjars/**");
//    }


}
