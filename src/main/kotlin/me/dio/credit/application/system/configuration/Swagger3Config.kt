package me.dio.credit.application.system.configuration

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.ExternalDocumentation
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Swagger3Config {
    @Bean
    fun publicApi(): GroupedOpenApi? {
        return GroupedOpenApi.builder()
            .group("springcreditapplicationsystem-public")
            .pathsToMatch("/api/customers/**", "/api/credits/**", "/api/admin/**", "/login")
            .build()
    }

    @Bean
    fun springOpenAPI(): OpenAPI {
        val securitySchema = "JWT_AUTH"
        return OpenAPI()
            .info(
                Info().title("API Credit Application System")
                    .description(
                        "Um sistema de aplicação de crédito que permite o gerenciamento de solicitações" +
                                " de crédito, cadastro de usuários, atualização de informações pessoais, exclusão de " +
                                "contas, pesquisa de créditos e concessão de crédito por administradores."
                    )
                    .contact(Contact().name("Lucas Couto").url("https://github.com/lucascouto91"))
                    .version("v0.0.1")
            )
            .externalDocs(
                ExternalDocumentation()
                    .description("Credit Application System")
                    .url("https://github.com/lucascouto91/credit-application-system")
            )
            .components(
                Components()
                    .addSecuritySchemes(
                        "bearer-key", SecurityScheme().type(SecurityScheme.Type.HTTP)
                            .scheme("Bearer").bearerFormat("JWT").`in`(SecurityScheme.In.HEADER)
                            .name("Authorization")
                    )
            )

    }


}