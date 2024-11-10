package team.yon2meet.backend.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import java.util.Optional

@Configuration
@EnableJpaAuditing(
    auditorAwareRef = "auditorAware"
)
class JpaAuditingConfiguration {
    @Bean
    fun auditorAware(): AuditorAware<String> {
        return AuditorAware { Optional.of("admin") }
    }
}
