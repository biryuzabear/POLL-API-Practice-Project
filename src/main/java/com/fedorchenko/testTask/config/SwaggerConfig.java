package com.fedorchenko.testTask.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(description = "test",
        contact = @Contact(name = "BOBA"),
        title = "tesy",
        version = "2.0.0"))
public class SwaggerConfig {
}
