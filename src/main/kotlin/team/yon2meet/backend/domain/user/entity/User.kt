package team.yon2meet.backend.domain.user.entity

import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.Size
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import team.yon2meet.backend.common.entity.AuditingEntityBase
import team.yon2meet.backend.enums.LoginType

@Entity
@Table(name = "user")
class User(
    @Column(columnDefinition = "varchar(50)")
    @Enumerated(EnumType.STRING)
    var loginType: LoginType,

    @Size(max = 255)
    @Column(name = "kakao_user_id")
    var kakaoUserId: String? = null,

    @Size(max = 255)
    @Column(name = "nickname")
    var nickname: String? = null,

    @Embedded
    var temporal: TemporalEmbeddable? = null,
) : AuditingEntityBase() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null
}
