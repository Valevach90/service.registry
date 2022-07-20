package com.andersen.banking.meeting_impl.config;

import io.swagger.v3.oas.models.OpenAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger Configuration.
 */

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

    /**
     * Generate OpenAPI configuration bean.
     */
    @Bean
    @ConfigurationProperties("openapi")
    public OpenAPI openApi() {
        return new OpenAPI();
    }
}
