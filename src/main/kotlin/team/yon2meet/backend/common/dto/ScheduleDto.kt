package team.yon2meet.backend.common.dto

import java.time.LocalDate
import java.time.LocalTime

data class ScheduleDto(
    val id: Long,
    val name: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val startTime: LocalTime,
    val endTimeExclusive: LocalTime,
    val numMaxMembers: Int?,
    val ownerId: Long,
)
