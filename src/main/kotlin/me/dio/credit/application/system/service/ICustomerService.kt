package me.dio.credit.application.system.service

import me.dio.credit.application.system.entity.Customer

interface ICustomerService {
    fun save(customer: me.dio.credit.application.system.entity.Customer): me.dio.credit.application.system.entity.Customer
    fun findById(id: Long): me.dio.credit.application.system.entity.Customer
    fun delete(id: Long)
}