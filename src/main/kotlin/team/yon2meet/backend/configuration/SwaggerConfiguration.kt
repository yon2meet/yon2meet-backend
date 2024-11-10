package team.yon2meet.backend.configuration

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import org.springdoc.core.utils.SpringDocUtils
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import team.yon2meet.backend.configuration.aop.annotation.UserId

@Configuration
class SwaggerConfiguration {
    @Bean
    fun api(): OpenAPI {
        val apiKey = io.swagger.v3.oas.models.security.SecurityScheme()
            .type(io.swagger.v3.oas.models.security.SecurityScheme.Type.APIKEY)
            .`in`(io.swagger.v3.oas.models.security.SecurityScheme.In.HEADER)
            .name("Authorization")
            .type(io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP)
            .scheme("bearer")
            .bearerFormat("JWT")
            .description("JWT Token")

        val securityRequirement = io.swagger.v3.oas.models.security.SecurityRequirement()
            .addList("Bearer Token")

        return OpenAPI()
            .components(Components().addSecuritySchemes("Bearer Token", apiKey))
            .addSecurityItem(securityRequirement)
    }

    companion object {
        init {
            SpringDocUtils.getConfig().addAnnotationsToIgnore(UserId::class.java)
        }
    }
}
