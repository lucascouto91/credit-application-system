package me.dio.credit.application.system.service

import me.dio.credit.application.system.entity.Credit
import java.util.UUID

interface ICreditService {
    fun save(credit: me.dio.credit.application.system.entity.Credit): me.dio.credit.application.system.entity.Credit
    fun findAllByCustomerId(customerId: Long): List<me.dio.credit.application.system.entity.Credit>
    fun findByCreditCode(customerId: Long, creditCode: UUID): me.dio.credit.application.system.entity.Credit

}