package com.example.apigatewayservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.*;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@Configuration
public class SwaggerConfig {

    @Bean
    public Docket swaggerConfiguration() {

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.apigatewayservice.controller"))
                .build()
                .apiInfo(apiDetails());

    }

    private ApiInfo apiDetails() {
        return new ApiInfoBuilder()
                .title("Carlsberg API")
                .description("api-gateway-service rest calls")
                .version("1.0")
                .build();
    }
}
