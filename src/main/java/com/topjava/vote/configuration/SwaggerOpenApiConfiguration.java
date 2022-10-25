package com.topjava.vote.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "basicAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "basic"
)
@OpenAPIDefinition(
        info = @Info(
                title = "Restaurant voting API",
                version = "1.0.0",
                description = """
                              <p>
                              <b>Test users:</b><br>
                              <b>Admin</b>: <b>Login</b>: frodo@ring.com <b>Pass</b>: admin<br>
                              <b>User</b>: <b>Login</b>: sam@ring.com <b>Pass</b>: user<br>
                              </p>
                              """
        ),
        security = @SecurityRequirement(name = "basicAuth")
)
public class SwaggerOpenApiConfiguration {
}
