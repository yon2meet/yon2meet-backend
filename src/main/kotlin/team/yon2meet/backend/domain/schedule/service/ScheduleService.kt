package team.yon2meet.backend.domain.schedule.service

import org.springframework.stereotype.Service
import team.yon2meet.backend.common.dto.ScheduleDto
import team.yon2meet.backend.domain.schedule.entity.Schedule
import team.yon2meet.backend.domain.schedule.repository.ScheduleRepository
import team.yon2meet.backend.domain.user.repository.UserRepository
import java.time.LocalDate
import java.time.LocalTime

@Service
class ScheduleService(
    private val userRepository: UserRepository,
    private val scheduleRepository: ScheduleRepository,
) {
    fun list(): List<ScheduleDto> {
        return scheduleRepository.findAll().map {
            ScheduleDto(
                id = it.id!!,
                name = it.name,
                startDate = it.startDate,
                endDate = it.endDate,
                startTime = it.startTime,
                endTimeExclusive = it.endTimeExclusive,
                numMaxMembers = it.numMaxMembers,
                ownerId = it.owner.id!!,
            )
        }
    }

    fun create(
        userId: Long,
        name: String,
        startDate: LocalDate,
        endDate: LocalDate,
        startTime: LocalTime,
        endTimeExclusive: LocalTime,
        numMaxMembers: Int?,
    ): ScheduleDto {
        if (numMaxMembers != null && numMaxMembers <= 0) {
            throw IllegalArgumentException("정원은 1 이상이어야 합니다")
        }

        val user = userRepository.findById(userId).orElseThrow()
        val schedule = Schedule(
            name = name,
            startDate = startDate,
            endDate = endDate,
            startTime = startTime,
            endTimeExclusive = endTimeExclusive,
            numMaxMembers = numMaxMembers,
            owner = user,
        ).run {
            scheduleRepository.save(this)
        }

        return ScheduleDto(
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
}
