package team.yon2meet.backend.domain.schedule.repository

import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import team.yon2meet.backend.domain.schedule.entity.Schedule
import team.yon2meet.backend.domain.schedule.entity.UserSchedule
import team.yon2meet.backend.domain.user.entity.User

@Repository
interface UserScheduleRepository : JpaRepository<UserSchedule, Long> {
    fun existsByUserAndSchedule(user: User, schedule: Schedule): Boolean

    fun countBySchedule(schedule: Schedule): Int

    @EntityGraph(attributePaths = ["schedule"])
    fun findAllByUserId(userId: Long): List<UserSchedule>

    fun findByUserAndSchedule(user: User, schedule: Schedule): UserSchedule?
}
