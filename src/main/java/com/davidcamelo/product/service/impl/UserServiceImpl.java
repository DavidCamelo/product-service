package com.davidcamelo.product.service.impl;

import com.davidcamelo.product.dto.FilterDTO;
import com.davidcamelo.product.dto.RestClientResponse;
import com.davidcamelo.product.dto.UserDTO;
import com.davidcamelo.product.error.ProductException;
import com.davidcamelo.product.service.UserService;
import com.davidcamelo.product.util.RestClientUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final RestClientUtil<UserDTO> restClientUtil;
    private static final String USER_SERVICE = "users";

    @Override
    public UserDTO create(UserDTO userDTO) {
        return handleResponse(restClientUtil.create(USER_SERVICE, userDTO, UserDTO.class));
    }

    @Override
    public UserDTO getById(Long id) {
        if (id == null) {
            return null;
        }
        return handleResponse(restClientUtil.getById(USER_SERVICE, id, UserDTO.class));
    }

    @Override
    public Page<UserDTO> getAll(FilterDTO filterDTO) {
        var response = restClientUtil.getAll(USER_SERVICE, filterDTO, Page.class);
        if (response.getDTO() != null) {
            return response.getDTO();
        }
        if (response.getErrorDTO() != null) {
            var errorList = List.of(UserDTO.builder().error(response.getErrorDTO()).build());
            return new PageImpl<>(errorList);
        }
        return null;
    }

    @Override
    public UserDTO update(Long id, UserDTO userDTO) {
        if (id == null) {
            return null;
        }
        return handleResponse(restClientUtil.update(USER_SERVICE, id, userDTO, UserDTO.class));
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            return;
        }
        var response = restClientUtil.delete(USER_SERVICE, id);
        if (response.getErrorDTO() != null) {
            throw new ProductException(response.getErrorDTO());
        }
    }

    private UserDTO handleResponse(RestClientResponse<UserDTO> userResponse) {
        if (userResponse.getDTO() != null) {
            return userResponse.getDTO();
        }
        if (userResponse.getErrorDTO() != null) {
            return UserDTO.builder().error(userResponse.getErrorDTO()).build();
        }
        return null;
    }
}
