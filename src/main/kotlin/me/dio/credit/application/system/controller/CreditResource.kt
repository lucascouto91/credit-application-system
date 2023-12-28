package me.dio.credit.application.system.controller

import jakarta.validation.Valid
import me.dio.credit.application.system.service.impl.CreditService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.stream.Collectors

@RestController
@RequestMapping("/api/credits")
class CreditResource(
    private val creditService: CreditService
) {

    @PostMapping
    fun saveCredit(@RequestBody @Valid creditDto: me.dio.credit.application.system.dto.CreditDto): ResponseEntity<String> {
        val credit: me.dio.credit.application.system.entity.Credit = this.creditService.save(creditDto.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED)
            .body("Credit ${credit.creditCode} - Customer ${credit.customer?.email} saved!")
    }

    @GetMapping
    fun fundAllByCustomerId(@RequestParam(value = "customerId") customerId: Long):
            ResponseEntity<List<me.dio.credit.application.system.dto.CreditViewList>> {
        val creditViewLists: List<me.dio.credit.application.system.dto.CreditViewList> = this.creditService.findAllByCustomerId(customerId).stream()
            .map { credit: me.dio.credit.application.system.entity.Credit ->
                me.dio.credit.application.system.dto.CreditViewList(
                    credit
                )
            }.collect(Collectors.toList())
        return ResponseEntity.status(HttpStatus.OK).body(creditViewLists)
    }

    @GetMapping("/{creditCode}")
    fun findByCreditCode(
        @RequestParam(value = "customerId") customerId: Long,
        @PathVariable creditCode: UUID
    ): ResponseEntity<me.dio.credit.application.system.dto.CreditView> {
        val credit: me.dio.credit.application.system.entity.Credit = this.creditService.findByCreditCode(customerId, creditCode)
        return ResponseEntity.status(HttpStatus.OK).body(me.dio.credit.application.system.dto.CreditView(credit))
    }


}