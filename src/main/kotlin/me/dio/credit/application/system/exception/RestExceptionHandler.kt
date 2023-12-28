package me.dio.credit.application.system.exception

import org.springframework.dao.DataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime

@RestControllerAdvice
class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handlerValidException(ex: MethodArgumentNotValidException): ResponseEntity<me.dio.credit.application.system.exception.ExceptionDetails> {
        val erros: MutableMap<String, String?> = HashMap()
        ex.bindingResult.allErrors.stream().forEach() { erro: ObjectError ->
            val fieldName: String = (erro as FieldError).field
            val messageError: String? = erro.defaultMessage
            erros[fieldName] = messageError
        }
        return ResponseEntity(
            me.dio.credit.application.system.exception.ExceptionDetails(
                title = "Bad Request! Consult the documentation ",
                timestamp = LocalDateTime.now(),
                status = HttpStatus.BAD_REQUEST.value(),
                exception = ex.javaClass.toString(),
                details = erros
            ), HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(DataAccessException::class)
    fun handlerValidException(ex: DataAccessException): ResponseEntity<me.dio.credit.application.system.exception.ExceptionDetails> {
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(
                me.dio.credit.application.system.exception.ExceptionDetails(
                    title = "Conflict! Consult the documentation ",
                    timestamp = LocalDateTime.now(),
                    status = HttpStatus.CONFLICT.value(),
                    exception = ex.javaClass.toString(),
                    details = mutableMapOf(ex.cause.toString() to ex.message)
                )
            )
        /*return ResponseEntity(
            ExceptionDetails(
                title = "Bad Request! Consult the documentation ",
                timestamp = LocalDateTime.now(),
                status = HttpStatus.CONFLICT.value(),
                exception = ex.javaClass.toString(),
                details = mutableMapOf(ex.cause.toString() to ex.message)
            ), HttpStatus.CONFLICT
        )*/
    }

    @ExceptionHandler(me.dio.credit.application.system.exception.BusinessException::class)
    fun handlerValidException(ex: me.dio.credit.application.system.exception.BusinessException): ResponseEntity<me.dio.credit.application.system.exception.ExceptionDetails> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(
                me.dio.credit.application.system.exception.ExceptionDetails(
                    title = "Bad Request! Consult the documentation ",
                    timestamp = LocalDateTime.now(),
                    status = HttpStatus.BAD_REQUEST.value(),
                    exception = ex.javaClass.toString(),
                    details = mutableMapOf(ex.cause.toString() to ex.message)
                )
            )
    }
    @ExceptionHandler(IllegalArgumentException::class)
    fun handlerValidException(ex: IllegalArgumentException): ResponseEntity<me.dio.credit.application.system.exception.ExceptionDetails> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(
                me.dio.credit.application.system.exception.ExceptionDetails(
                    title = "Bad Request! Consult the documentation ",
                    timestamp = LocalDateTime.now(),
                    status = HttpStatus.BAD_REQUEST.value(),
                    exception = ex.javaClass.toString(),
                    details = mutableMapOf(ex.cause.toString() to ex.message)
                )
            )
    }
}
