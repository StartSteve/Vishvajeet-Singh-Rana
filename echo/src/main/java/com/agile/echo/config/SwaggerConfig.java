package com.agile.echo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

    public static final String SCHEME_NAME = "BearerScheme";
    public static final String SCHEME = "Bearer";


    @Bean
    public OpenAPI customOpenAPI() {
        var openApi = new OpenAPI().info(this.apiInfo());
        this.addSecurity(openApi);
        return openApi;
    }

    private Info apiInfo() {
        var contact = new Contact();
        contact.setEmail("dev@tekmindz.com");
        contact.setName("product_admin");
        contact.setUrl("https://tekmindz.com");
        return new Info()
                .title("Product API")
                .description("Product description")
                .termsOfService("https://tekmindz.com/terms_of_service")
                .contact(contact)
                .version("v1");
    }

    private void addSecurity(OpenAPI openApi) {
        var components = this.createComponents();
        var securityItem = new SecurityRequirement().addList(SCHEME_NAME);
        openApi.components(components).addSecurityItem(securityItem);
    }

    private Components createComponents() {
        var components = new Components();
        components.addSecuritySchemes(SCHEME_NAME, this.createSecurityScheme());
        return components;
    }

    private SecurityScheme createSecurityScheme() {
        return new SecurityScheme().name(SCHEME_NAME).type(SecurityScheme.Type.HTTP).scheme(SCHEME);
    }
}