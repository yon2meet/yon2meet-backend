package team.yon2meet.backend.domain.schedule.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import team.yon2meet.backend.common.dto.UserScheduleDto
import team.yon2meet.backend.configuration.aop.annotation.UserId
import team.yon2meet.backend.domain.schedule.controller.response.SchedulesResponse
import team.yon2meet.backend.domain.schedule.service.UserScheduleService

@RestController
@RequestMapping("/me/schedules")
class UserScheduleController(
    private val userScheduleService: UserScheduleService,
) {
    @GetMapping
    fun list(
        @UserId
        userId: Long,
    ): SchedulesResponse {
        val schedules = userScheduleService.list(
            userId = userId,
        )
        return SchedulesResponse(schedules)
    }

    @PostMapping("/{scheduleId}")
    fun create(
        @UserId
        userId: Long,
        @PathVariable
        scheduleId: Long,
    ): UserScheduleDto {
        return userScheduleService.create(
            userId = userId,
            scheduleId = scheduleId,
        )
    }

    @DeleteMapping("/{scheduleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(
        @UserId
        userId: Long,
        @PathVariable
        scheduleId: Long,
    ) {
        userScheduleService.delete(
            userId = userId,
            scheduleId = scheduleId,
        )
    }
}
