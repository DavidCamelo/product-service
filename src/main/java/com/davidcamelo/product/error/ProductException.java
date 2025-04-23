package com.davidcamelo.product.error;

import com.davidcamelo.product.dto.ErrorDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductException extends RuntimeException {

    public ProductException(ErrorDTO errorDTO) {
        super(errorDTO.message());
        log.error("Error message: {}, timestamp: {}", errorDTO.message(), errorDTO.timestamp(), this);
    }
}
