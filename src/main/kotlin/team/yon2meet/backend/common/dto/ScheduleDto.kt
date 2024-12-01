package team.yon2meet.backend.common.dto

import team.yon2meet.backend.domain.schedule.entity.Schedule
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
) {
    constructor(
        schedule: Schedule,
    ) : this(
        id = schedule.id!!,
        name = schedule.name,
        startDate = schedule.startDate,
        endDate = schedule.endDate,
        startTime = schedule.startTime,
        endTimeExclusive = schedule.endTimeExclusive,
        numMaxMembers = schedule.numMaxMembers,
        ownerId = schedule.owner.id!!,
    )
}
