package me.dio.credit.application.system.entity

import jakarta.persistence.*
import me.dio.credit.application.system.enummeration.Status
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

@Entity
//@Table(name = "Credit")
class Credit (
    @Column(nullable = false, unique = true) var creditCode: UUID = UUID.randomUUID(),
    @Column(nullable = false) val creditValue: BigDecimal = BigDecimal.ZERO,
    @Column(nullable = false) val dayFirstInstallment: LocalDate,
    @Column(nullable = false) val numberOfInstallments: Int = 0,
    @Enumerated val status: me.dio.credit.application.system.enummeration.Status = me.dio.credit.application.system.enummeration.Status.IN_PROGRESS,
    @ManyToOne var customer: me.dio.credit.application.system.entity.Customer? = null,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null
)
