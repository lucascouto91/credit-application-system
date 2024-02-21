package me.dio.credit.application.system.controller

import jakarta.validation.Valid
import me.dio.credit.application.system.dto.CreditDto
import me.dio.credit.application.system.dto.CreditView
import me.dio.credit.application.system.dto.CreditViewList
import me.dio.credit.application.system.entity.Credit
import me.dio.credit.application.system.security.UserCanOnlyAccessTheirOwnResource
import me.dio.credit.application.system.service.impl.CreditService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.stream.Collectors

@RestController
@RequestMapping("/api/credits")
class CreditResource(
    private val creditService: CreditService
) {

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    fun saveCredit(@RequestBody @Valid creditDto: CreditDto): ResponseEntity<String> {
        val credit: Credit = this.creditService.save(creditDto.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED)
            .body("Credit ${credit.creditCode} - Customer ${credit.customer?.email} saved!")
    }

    @GetMapping
    @UserCanOnlyAccessTheirOwnResource
    fun fundAllByCustomerId(@RequestParam(value = "customerId") id: Long):
            ResponseEntity<List<CreditViewList>> {
        val creditViewLists: List<CreditViewList> = this.creditService.findAllByCustomerId(id).stream()
            .map { credit: Credit ->
                CreditViewList(
                    credit
                )
            }.collect(Collectors.toList())
        return ResponseEntity.status(HttpStatus.OK).body(creditViewLists)
    }

    @GetMapping("/{creditCode}")
    @UserCanOnlyAccessTheirOwnResource
    fun findByCreditCode(
        @RequestParam(value = "customerId") id: Long,
        @PathVariable creditCode: UUID
    ): ResponseEntity<CreditView> {
        val credit: Credit = this.creditService.findByCreditCode(id, creditCode)
        return ResponseEntity.status(HttpStatus.OK).body(CreditView(credit))
    }


}