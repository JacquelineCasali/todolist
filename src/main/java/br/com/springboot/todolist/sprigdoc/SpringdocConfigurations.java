package br.com.springboot.todolist.sprigdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Configuration classe de configuração da documentacao
@Configuration
public class SpringdocConfigurations {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                //@OpenAPIDefinition(
                .info(new Info().title("Tarefas")
                        .description("Api responsável por listar tarefas")
                        .version("1")
                )


                .components(new Components()
                        .addSecuritySchemes("basic_auth",
                                new SecurityScheme()
                                        .name("basic_auth")
                                        .type(SecurityScheme.Type.HTTP).scheme("basic").bearerFormat("Basic")

                        ));

    }

}
