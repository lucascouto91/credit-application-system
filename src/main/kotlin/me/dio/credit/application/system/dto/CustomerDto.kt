package me.dio.credit.application.system.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import me.dio.credit.application.system.entity.Address
import me.dio.credit.application.system.entity.Customer
import org.hibernate.validator.constraints.br.CPF
import java.math.BigDecimal

data class CustomerDto(
    @field:NotBlank(message = "Invalid input") val firstName: String,
    @field:NotBlank(message = "Invalid input") val lastName: String,
    @field:NotBlank(message = "Invalid input")
    @field:CPF(message = "This invalid CPF") val cpf: String,
    @field:NotNull(message = "Invalid input") val income: BigDecimal,
    @field:Email(message = "Invalid email")
    @field:NotBlank(message = "Invalid input") val email: String,
    @field:NotBlank(message = "Invalid input") val password: String,
    @field:NotBlank(message = "Invalid input") val zipCode: String,
    @field:NotBlank(message = "Invalid input") val street: String
) {

    fun toEntity(): me.dio.credit.application.system.entity.Customer = me.dio.credit.application.system.entity.Customer(
        firstName = this.firstName,
        lastName = this.lastName,
        cpf = this.cpf,
        income = this.income,
        email = this.email,
        password = this.password,
        address = me.dio.credit.application.system.entity.Address(
            zipCode = this.zipCode,
            street = this.street
        )

    )
}