package team.yon2meet.backend.domain.schedule.controller

import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import team.yon2meet.backend.common.dto.ScheduleDto
import team.yon2meet.backend.configuration.aop.annotation.UserId
import team.yon2meet.backend.domain.schedule.controller.response.SchedulesResponse
import team.yon2meet.backend.domain.schedule.service.ScheduleService
import java.time.LocalDate
import java.time.LocalTime

@RestController
@RequestMapping("/schedules")
class ScheduleController(
    private val scheduleService: ScheduleService,
) {
    @GetMapping
    @Operation(summary = "[개발용] 존재하는 전체 일정 목록 조회")
    fun list(): SchedulesResponse {
        val schedules = scheduleService.list()
        return SchedulesResponse(schedules)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "일정 생성")
    fun create(
        @UserId
        userId: Long,
        @RequestBody
        request: CreateScheduleRequest,
    ): ScheduleDto {
        return scheduleService.create(
            userId = userId,
            name = request.name,
            startDate = request.startDate,
            endDate = request.endDate,
            startTime = request.startTime,
            endTimeExclusive = request.endTimeExclusive,
            numMaxMembers = request.numMaxMembers,
        )
    }
}

data class CreateScheduleRequest(
    val name: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val startTime: LocalTime,
    val endTimeExclusive: LocalTime,
    val numMaxMembers: Int?,
)
