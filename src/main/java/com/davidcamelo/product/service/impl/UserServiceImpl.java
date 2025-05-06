package com.davidcamelo.product.service.impl;

import com.davidcamelo.product.dto.ErrorDTO;
import com.davidcamelo.product.dto.UserDTO;
import com.davidcamelo.product.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final RestClient.Builder restClientBuilder;

    @Override
    public UserDTO getById(Long id) {
        if (id != null) {
            try {
                return restClientBuilder.build().get().uri("http://user/{id}", id).retrieve().body(UserDTO.class);
            } catch (Exception ex) {
                if (ex instanceof HttpClientErrorException httpEx) {
                    return UserDTO.builder().id(id).error(httpEx.getResponseBodyAs(ErrorDTO.class)).build();
                }
                return UserDTO.builder().id(id).error(ErrorDTO.builder().message(ex.getMessage()).timestamp(new Date()).build()).build();
            }
        }
        return UserDTO.builder().build();
    }
}
