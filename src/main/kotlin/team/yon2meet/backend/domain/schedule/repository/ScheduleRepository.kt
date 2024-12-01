package team.yon2meet.backend.domain.schedule.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import team.yon2meet.backend.domain.schedule.entity.Schedule

@Repository
interface ScheduleRepository : JpaRepository<Schedule, Long>
