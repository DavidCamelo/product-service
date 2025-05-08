package com.davidcamelo.product.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserDTO (
        Long id,
        String name,
        String lastName,
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        ErrorDTO error
) { }
