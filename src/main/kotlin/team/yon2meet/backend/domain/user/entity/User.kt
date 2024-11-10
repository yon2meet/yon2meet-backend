package team.yon2meet.backend.domain.user.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import team.yon2meet.backend.enums.LoginType
import java.time.Instant

@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener::class)
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
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    @NotNull
    @CreatedDate
    @Column(name = "created_at", nullable = false)
    var createdAt: Instant? = null

    @Size(max = 255)
    @NotNull
    @CreatedBy
    @Column(name = "created_by", nullable = false)
    var createdBy: String? = null

    @NotNull
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    var updatedAt: Instant? = null

    @Size(max = 255)
    @NotNull
    @LastModifiedBy
    @Column(name = "updated_by", nullable = false)
    var updatedBy: String? = null
}
