package team.yon2meet.backend.domain.schedule.controller.response

import team.yon2meet.backend.common.dto.ScheduleDto

data class SchedulesResponse(
    val schedules: List<ScheduleDto>,
)
