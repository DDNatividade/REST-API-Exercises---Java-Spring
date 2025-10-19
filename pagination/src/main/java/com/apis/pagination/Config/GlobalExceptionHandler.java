package com.apis.pagination.Config;


import com.apis.pagination.DTO.ErrorResponse;
import com.apis.pagination.Exception.ProductNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<ErrorResponse> productNotFound
            (ProductNotFound exception){
        ErrorResponse error = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Product not found",
                LocalDateTime.now()
        );

         return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
