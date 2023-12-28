package me.dio.credit.application.system.service.impl

import me.dio.credit.application.system.repository.CustomerRepository
import me.dio.credit.application.system.service.ICustomerService
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository
) : ICustomerService {
    override fun save(customer: me.dio.credit.application.system.entity.Customer): me.dio.credit.application.system.entity.Customer =
        this.customerRepository.save(customer)


    override fun findById(id: Long): me.dio.credit.application.system.entity.Customer =
        this.customerRepository.findById(id).orElseThrow {
            throw me.dio.credit.application.system.exception.BusinessException("Id $id not found")
        }


    override fun delete(id: Long) {
        val customer: me.dio.credit.application.system.entity.Customer = this.findById(id)
        this.customerRepository.delete(customer)
    }
}
