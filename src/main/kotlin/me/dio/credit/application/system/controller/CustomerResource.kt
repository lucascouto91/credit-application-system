package me.dio.credit.application.system.controller

import jakarta.validation.Valid
import me.dio.credit.application.system.service.impl.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/customers")
class CustomerResource(
    private val customerService: CustomerService
) {
    @PostMapping
    fun saveCustomer(@RequestBody @Valid customerDto: me.dio.credit.application.system.dto.CustomerDto): ResponseEntity<me.dio.credit.application.system.dto.CustomerView> {
        val savedCustomer: me.dio.credit.application.system.entity.Customer = this.customerService.save(customerDto.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body(
            me.dio.credit.application.system.dto.CustomerView(
                savedCustomer
            )
        )
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<me.dio.credit.application.system.dto.CustomerView> {
        val customer: me.dio.credit.application.system.entity.Customer = this.customerService.findById(id)
        return ResponseEntity.status(HttpStatus.OK).body(me.dio.credit.application.system.dto.CustomerView(customer))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCustomer(@PathVariable id: Long) = this.customerService.delete(id)

    @PatchMapping
    fun updateCustomer(
        @RequestParam(value = "customerId") id: Long,
        @RequestBody @Valid customerUpdateDto: me.dio.credit.application.system.dto.CustomerUpdateDto
    ): ResponseEntity<me.dio.credit.application.system.dto.CustomerView> {
        val customer: me.dio.credit.application.system.entity.Customer = this.customerService.findById(id)
        val customerToUpdate: me.dio.credit.application.system.entity.Customer = customerUpdateDto.toEntity(customer)
        val customerUpdated: me.dio.credit.application.system.entity.Customer = this.customerService.save(customerToUpdate)
        return ResponseEntity.status(HttpStatus.OK).body(
            me.dio.credit.application.system.dto.CustomerView(
                customerUpdated
            )
        )
    }
}