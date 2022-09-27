package com.andersen.banking.meeting_impl.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Credit Service",
    description = "This is the API for the Credit Service of the MeetingRoom New project", version = "v1"))
public class OpenApiConfig {}
