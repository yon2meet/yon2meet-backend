package team.yon2meet.backend.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import team.yon2meet.backend.BASE_PACKAGE

@Configuration
@EnableJpaRepositories(
    basePackages = [BASE_PACKAGE]
)
class JpaConfiguration
