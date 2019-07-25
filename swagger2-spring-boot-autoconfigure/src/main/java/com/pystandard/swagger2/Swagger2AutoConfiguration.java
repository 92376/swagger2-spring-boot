package com.pystandard.swagger2;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * 自动装配
 *
 * @author wujing
 * @since 2019/7/23 11:53
 */
@Configuration
@EnableSwagger2
@EnableConfigurationProperties(com.pystandard.swagger2.Swagger2Properties.class)
@ConditionalOnProperty(prefix = "swagger2", name = "enable", havingValue = "true")
public class Swagger2AutoConfiguration {

    @Autowired
    private Swagger2Properties swagger2Properties;

    /**
     * header
     */
    private static final String HEADER = "header";

    /**
     * 全局header参数token
     */
    private static final String TOKEN = "token";

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                // 启用
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    @Bean
    public UiConfiguration uiConfiguration() {
        UiConfigurationBuilder builder = UiConfigurationBuilder.builder();
        return builder.filter(true).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(swagger2Properties.getTitle())
                .version(swagger2Properties.getVersion())
                .build();
    }

    private List<ApiKey> securitySchemes() {

        List<ApiKey> list = new ArrayList<>();
        list.add(new ApiKey(TOKEN, TOKEN, HEADER));
        return list;
    }

    private List<SecurityContext> securityContexts() {

        List<SecurityContext> list = new ArrayList<>();
        list.add(SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.any())
                .build());
        return list;
    }

    private List<SecurityReference> defaultAuth() {

        List<SecurityReference> list = new ArrayList<>();
        // 暂时不知道什么用
        AuthorizationScope authorizationScope = new AuthorizationScope("", "");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        list.add(new SecurityReference(TOKEN, authorizationScopes));

        return list;
    }

}