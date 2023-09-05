package dev.ashutosh.ProductService.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ExecptionDto {
    private String message;
    private HttpStatus errorCode;
    public ExecptionDto(HttpStatus status, String message) {
        this.message = message;
        this.errorCode = status;
    }

}
