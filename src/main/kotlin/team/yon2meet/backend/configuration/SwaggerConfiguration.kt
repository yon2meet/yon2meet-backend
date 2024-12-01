package team.yon2meet.backend.configuration

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.ExternalDocumentation
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.media.Schema
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springdoc.core.utils.SpringDocUtils
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import team.yon2meet.backend.configuration.aop.annotation.UserId
import java.awt.Color
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Configuration
class SwaggerConfiguration {
    @Bean
    fun api(): OpenAPI {
        val apiKey = SecurityScheme()
            .type(SecurityScheme.Type.APIKEY)
            .`in`(SecurityScheme.In.HEADER)
            .name("Authorization")
            .type(SecurityScheme.Type.HTTP)
            .scheme("bearer")
            .bearerFormat("JWT")
            .description("JWT Token")

        val securityRequirement = SecurityRequirement()
            .addList("Bearer Token")

        val components = Components()
            .addSecuritySchemes("Bearer Token", apiKey)

        return OpenAPI()
            .components(components)
            .addSecurityItem(securityRequirement)
            .info(
                Info()
                    .title("GDGoC 2024 oTP - Yon2Meet")
                    .description(description)
                    .version("prototype"),
            )
            .externalDocs(
                ExternalDocumentation()
                    .description("GDGoC 2024 oTP - Yon2Meet"),
            )
    }

    private val description = """
        ## Swagger 사용법

        ### 인증

        오른쪽 아래의 "Authorize" 버튼을 눌러 토큰을 입력하고 확인 버튼을 누르면 API를 사용할 수 있습니다.
        버튼을 찾기 어렵다면 Ctrl + F 또는 Cmd + F 를 눌러 찾기 기능으로 검색하실 수 있습니다.

        ### API 호출

        하단에 표시되는 각 API를 expand한 다음, Try it out 버튼을 눌러 파라미터 입력 화면을 띄웁니다.
        파라미터를 형식에 맞게 입력한 다음, Execute 버튼을 누르면 API를 호출할 수 있습니다.

        ## 클라이언트 적용

        ### 필수 요청 헤더

        필요한 요청 헤더 2개는 다음과 같습니다. <br />
        이때 `{토큰}` 대신 발급된 토큰을 넣어주세요. <br />

        * "Authorization: Bearer `{토큰}`"
        * "Content-Type: application/json"

        ### cURL 예시

        본 문서에서 API 호출을 시도해볼 경우, 해당 요청에 대한 cURL 코드가 생성됩니다.
        """.trimIndent()

    companion object {
        init {
            SpringDocUtils
                .getConfig()
                .addAnnotationsToIgnore(UserId::class.java)
                .replaceWithSchema(
                    Color::class.java,
                    Schema<String>()
                        .type("string")
                        .format("color")
                        .example("#FFFFFFFF"),
                )
                .replaceWithSchema(
                    LocalTime::class.java,
                    Schema<LocalTime>()
                        .type("string")
                        .format("time")
                        .example(
                            LocalTime
                                .now()
                                .format(DateTimeFormatter.ofPattern("HH:mm:ss")),
                        ),
                )
        }
    }
}
