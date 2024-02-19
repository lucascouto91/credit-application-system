package me.dio.credit.application.system.service.impl

import me.dio.credit.application.system.entity.Customer
import me.dio.credit.application.system.enummeration.Roles
import me.dio.credit.application.system.repository.CustomerRepository
import me.dio.credit.application.system.service.ICustomerService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository,
    private val bCrypt: BCryptPasswordEncoder
) : ICustomerService {
    override fun save(customer: Customer): Customer {
        val customerCopy = customer.copy(
            roles = setOf(Roles.CUSTOMER),
            password = bCrypt.encode(customer.password)
        )
        return customerRepository.save(customerCopy)
    }


    override fun findById(id: Long): Customer =
        this.customerRepository.findById(id).orElseThrow {
            throw me.dio.credit.application.system.exception.BusinessException("Id $id not found")
        }


    override fun delete(id: Long) {
        val customer: Customer = this.findById(id)
        this.customerRepository.delete(customer)
    }
}
