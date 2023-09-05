package dev.ashutosh.ProductService.exceptions;

import dev.ashutosh.ProductService.dtos.ExecptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvices {
    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<ExecptionDto> handleNotFoundException(NotFoundException notFoundException){
            return new ResponseEntity<>(
                    new ExecptionDto(HttpStatus.NOT_FOUND, notFoundException.getMessage()), HttpStatus.NOT_FOUND);
    }
}
