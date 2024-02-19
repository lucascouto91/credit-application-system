package me.dio.credit.application.system.repository

import me.dio.credit.application.system.dto.CustomerDto
import me.dio.credit.application.system.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository: JpaRepository<Customer, Long> {
    fun findByEmail(email: String): Customer?
}