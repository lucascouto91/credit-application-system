package me.dio.credit.application.system.service.impl

import me.dio.credit.application.system.repository.CreditRepository
import me.dio.credit.application.system.service.ICreditService
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
class CreditService(
    private val creditRepository: CreditRepository,
    private val customerService: CustomerService
): ICreditService {
    override fun save(credit: me.dio.credit.application.system.entity.Credit): me.dio.credit.application.system.entity.Credit {
        this.validDayFirstInstallment(credit.dayFirstInstallment)
        credit.apply {
            customer = customerService.findById(credit.customer?.id!!)
        }
        return this.creditRepository.save(credit)
    }

    override fun findAllByCustomerId(customerId: Long): List<me.dio.credit.application.system.entity.Credit> =
        this.creditRepository.findAllByCustomerId(customerId)

    override fun findByCreditCode(customerId: Long, creditCode: UUID): me.dio.credit.application.system.entity.Credit {
        val credit: me.dio.credit.application.system.entity.Credit = (this.creditRepository.findByCreditCode(creditCode)
            ?: throw me.dio.credit.application.system.exception.BusinessException("Creditcode $creditCode not found"))
        return if (credit.customer?.id == customerId) {
            credit
        } else {
            throw IllegalArgumentException("Contact admin")
        }
    }
    private fun validDayFirstInstallment(dayFirstInstallment: LocalDate): Boolean {
        return if (dayFirstInstallment.isBefore(LocalDate.now().plusMonths(3))) true
        else throw me.dio.credit.application.system.exception.BusinessException("Invalid Date")
    }
}