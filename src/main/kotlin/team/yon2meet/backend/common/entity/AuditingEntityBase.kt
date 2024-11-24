package team.yon2meet.backend.common.entity

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class AuditingEntityBase {
    @NotNull
    @CreatedDate
    @Column(name = "created_at", nullable = false)
    var createdAt: Instant? = null

    @Size(max = 20)
    @NotNull
    @CreatedBy
    @Column(name = "created_by", nullable = false)
    var createdBy: String? = null

    @NotNull
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    var updatedAt: Instant? = null

    @Size(max = 20)
    @NotNull
    @LastModifiedBy
    @Column(name = "updated_by", nullable = false)
    var updatedBy: String? = null
}
