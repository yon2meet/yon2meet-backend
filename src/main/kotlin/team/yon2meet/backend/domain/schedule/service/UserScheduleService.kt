package team.yon2meet.backend.domain.schedule.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team.yon2meet.backend.common.dto.ScheduleDto
import team.yon2meet.backend.common.dto.UserScheduleDto
import team.yon2meet.backend.domain.schedule.entity.UserSchedule
import team.yon2meet.backend.domain.schedule.repository.ScheduleRepository
import team.yon2meet.backend.domain.schedule.repository.UserScheduleRepository
import team.yon2meet.backend.domain.user.repository.UserRepository

@Service
class UserScheduleService(
    private val userRepository: UserRepository,
    private val userScheduleRepository: UserScheduleRepository,
    private val scheduleRepository: ScheduleRepository,
) {
    fun list(
        userId: Long,
    ): List<ScheduleDto> {
        return userScheduleRepository
            .findAllByUserId(userId)
            .map {
                ScheduleDto(it.schedule)
            }
    }

    @Transactional
    fun create(
        userId: Long,
        scheduleId: Long,
    ): UserScheduleDto {
        val user = userRepository.findById(userId).orElseThrow()
        val schedule = scheduleRepository.findById(scheduleId).orElseThrow()

        if (userScheduleRepository.existsByUserAndSchedule(user, schedule)) {
            throw IllegalArgumentException("이미 참여한 일정입니다")
        }

        val numMaxMembers = schedule.numMaxMembers

        if (numMaxMembers != null && numMaxMembers <= userScheduleRepository.countBySchedule(schedule)) {
            throw IllegalArgumentException("정원이 초과되었습니다")
        }

        val userSchedule = UserSchedule(
            user = user,
            schedule = schedule,
        ).run {
            userScheduleRepository.save(this)
        }

        return UserScheduleDto(
            id = userSchedule.id!!,
            userId = user.id!!,
            scheduleId = schedule.id!!,
        )
    }

    @Transactional
    fun delete(
        userId: Long,
        scheduleId: Long,
    ) {
        val user = userRepository.findById(userId).orElseThrow()
        val schedule = scheduleRepository.findById(scheduleId).orElseThrow()

        if (schedule.owner == user) {
            throw IllegalArgumentException("일정 소유자는 참여를 취소할 수 없습니다")
        }

        val userSchedule = userScheduleRepository
            .findByUserAndSchedule(user, schedule)
            ?: throw IllegalArgumentException("참여하지 않은 일정입니다")

        userScheduleRepository.delete(userSchedule)
    }
}
