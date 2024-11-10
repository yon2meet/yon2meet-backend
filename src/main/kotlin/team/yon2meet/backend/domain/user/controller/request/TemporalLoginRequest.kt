package team.yon2meet.backend.domain.user.controller.request

data class TemporalLoginRequest(
    val username: String,
    val password: String,
    val scheduleId: Long,
)
