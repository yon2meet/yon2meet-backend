package team.yon2meet.backend.domain.schedule.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import jakarta.validation.constraints.Size
import team.yon2meet.backend.common.entity.AuditingEntityBase
import team.yon2meet.backend.domain.user.entity.User
import java.time.LocalDate
import java.time.LocalTime

@Entity
@Table(name = "schedule")
class Schedule(
    @Size(max = 255)
    @Column(name = "name")
    var name: String,

    @Column(name = "start_date")
    var startDate: LocalDate,

    @Column(name = "end_date")
    var endDate: LocalDate,

    @Column(name = "start_time")
    var startTime: LocalTime,

    @Column(name = "end_time_exclusive")
    var endTimeExclusive: LocalTime,

    @Column(name = "num_max_members")
    var numMaxMembers: Int?,

    @ManyToOne
    var owner: User,
) : AuditingEntityBase() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null
}
