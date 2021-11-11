package com.web.demo.service.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class MyConfig {
    //配置swagger2核心配置docket
    //修改默认配置bean
    @Bean
    public Docket myDocket(){
        return new Docket(DocumentationType.SWAGGER_2)  //指定API类型为swagger2
                .apiInfo(apiInfo())  //用于定于api文档汇总信息
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.web.demo.service.controller"))  //指定controller包
                .paths(PathSelectors.any())        // 所有controller
                .build();
    }


    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Web前后端基础开发实战")     // 文档页标题
                .contact(new Contact("Cxx",  // 联系人信息
                        "www.baidu.com",
                        "@email"))
                .description("Web前后端基础开发实战")   // 详细信息
                .version("1.0.1")   // 文档版本号
                .termsOfServiceUrl("www.baidu.com")  //网站地址
                .build();
    }
}