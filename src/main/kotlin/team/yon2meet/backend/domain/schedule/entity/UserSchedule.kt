package team.yon2meet.backend.domain.schedule.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import team.yon2meet.backend.common.entity.AuditingEntityBase
import team.yon2meet.backend.domain.user.entity.User

@Entity
@Table(name = "user_schedule")
class UserSchedule(
    @ManyToOne
    var user: User,

    @ManyToOne
    var schedule: Schedule,
) : AuditingEntityBase() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null
}
