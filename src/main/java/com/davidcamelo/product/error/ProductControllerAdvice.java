package com.davidcamelo.product.error;

import com.davidcamelo.product.api.ProductController;
import com.davidcamelo.product.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice(assignableTypes = { ProductController.class })
public class ProductControllerAdvice {

    @ExceptionHandler(value = { ProductException.class })
    public ResponseEntity<ErrorDTO> handleProductException(ProductException ex) {
        return new ResponseEntity<>(ErrorDTO.builder().message(ex.getMessage()).timestamp(new Date()).build(), HttpStatus.NOT_FOUND);
    }
}
