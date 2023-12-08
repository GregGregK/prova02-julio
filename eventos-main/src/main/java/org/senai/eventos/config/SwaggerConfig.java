package org.senai.eventos.config;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Prova 02 API")
                        .description("API para prova 02 ")
                        .version("1.0")

                ).externalDocs(
                        new ExternalDocumentation()
                                .description("Gregori Rodrigues Monteiro"));
    }
}

