package me.dio.credit.application.system.dto

import me.dio.credit.application.system.entity.Customer
import me.dio.credit.application.system.enummeration.CustomerStatus
import me.dio.credit.application.system.enummeration.Roles
import java.math.BigDecimal

data class CustomerView(
    val customerStatus: CustomerStatus,
    val firstName: String,
    val lastName: String,
    val cpf: String,
    val income: BigDecimal,
    val email: String,
    val password: String,
    val zipCode: String,
    val street: String,
    val id: Long?,
    val role: Set<Roles>
) {
    constructor(customer: Customer): this (
        customerStatus = customer.customerStatus,
        firstName = customer.firstName,
        lastName = customer.lastName,
        cpf = customer.cpf,
        income = customer.income,
        email = customer.email,
        password = customer.password,
        zipCode = customer.address.zipCode,
        street = customer.address.street,
        id = customer.id,
        role = customer.roles
    )

}
