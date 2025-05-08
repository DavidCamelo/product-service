package com.davidcamelo.product.service.impl;

import com.davidcamelo.product.dto.UserDTO;
import com.davidcamelo.product.error.ProductException;
import com.davidcamelo.product.service.UserService;
import com.davidcamelo.product.util.RestClientUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final RestClientUtil<UserDTO> restClientUtil;
    private static final String USER_SERVICE = "user";

    @Override
    public UserDTO create(UserDTO userDTO) {
        var response = restClientUtil.create(USER_SERVICE, userDTO, UserDTO.class);
        if (response.getDTO() != null) {
            return response.getDTO();
        }
        return UserDTO.builder().error(response.getErrorDTO()).build();
    }

    @Override
    public UserDTO getById(Long id) {
        var response = restClientUtil.getById(USER_SERVICE, id, UserDTO.class);
        if (response.getDTO() != null) {
            return response.getDTO();
        }
        return UserDTO.builder().id(id).error(response.getErrorDTO()).build();
    }

    @Override
    public List<UserDTO> getAll() {
        var response = restClientUtil.getAll(USER_SERVICE, UserDTO[].class);
        if (response.getDTO() != null) {
            return response.getDTO();
        }
        if (response.getErrorDTO() != null) {
            return List.of(UserDTO.builder().error(response.getErrorDTO()).build());
        }
        return new ArrayList<>();
    }

    @Override
    public UserDTO update(Long id, UserDTO userDTO) {
        var response = restClientUtil.update(USER_SERVICE, id, userDTO, UserDTO.class);
        if (response.getDTO() != null) {
            return response.getDTO();
        }
        return UserDTO.builder().error(response.getErrorDTO()).build();
    }

    @Override
    public void delete(Long id) {
        var response = restClientUtil.delete(USER_SERVICE, id);
        if (response.getErrorDTO() != null) {
            throw new ProductException(response.getErrorDTO());
        }
    }
}
