package me.dio.credit.application.system.dto


import me.dio.credit.application.system.entity.Credit
import me.dio.credit.application.system.enummeration.CreditStatus
import java.math.BigDecimal
import java.util.*

data class CreditView(
    val creditCode: UUID,
    val creditValue: BigDecimal,
    val numberOfInstallment: Int,
    val creditStatus: CreditStatus,
    val emailCustomer: String?,
    val incomeCustomer: BigDecimal?
) {
    constructor(credit: Credit) : this(
        creditCode = credit.creditCode,
        creditValue = credit.creditValue,
        numberOfInstallment = credit.numberOfInstallments,
        creditStatus = credit.creditStatus,
        emailCustomer = credit.customer?.email,
        incomeCustomer = credit.customer?.income
    )

}
