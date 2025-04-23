package com.davidcamelo.product.service.impl;

import com.davidcamelo.product.dto.ErrorDTO;
import com.davidcamelo.product.dto.UserDTO;
import com.davidcamelo.product.error.ProductException;
import com.davidcamelo.product.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final RestClient.Builder restClientBuilder;
    private final ObjectMapper objectMapper;

    @Override
    public UserDTO getById(Long id) {
        if (id != null) {
            try {
                return restClientBuilder.build()
                        .get()
                        .uri("http://user/{id}", id)
                        .exchange((request, response) -> {
                            if (response.getStatusCode().isError()) {
                                throw new ProductException(objectMapper.readValue(response.getBody(), ErrorDTO.class));
                            } else {
                                return objectMapper.readValue(response.getBody(), UserDTO.class);
                            }
                        });
            } catch (Exception ex) {
                return UserDTO.builder().id(id).error(ErrorDTO.builder().message(ex.getMessage()).timestamp(new Date()).build()).build();
            }
        }
        return UserDTO.builder().build();
    }
}
