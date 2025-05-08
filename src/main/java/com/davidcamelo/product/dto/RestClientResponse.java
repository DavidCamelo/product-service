package com.davidcamelo.product.dto;

import lombok.Data;

@Data
public class RestClientResponse<T> {
    private T DTO;
    private ErrorDTO errorDTO;
}
