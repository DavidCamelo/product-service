package com.davidcamelo.product.service.impl;

import com.davidcamelo.product.dto.FilterDTO;
import com.davidcamelo.product.dto.UserDTO;
import com.davidcamelo.product.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final String USER_SERVICE = "users";

    @Override
    public UserDTO create(UserDTO userDTO) {
        return userDTO;
    }

    @Override
    public UserDTO getById(Long id) {
        if (id == null) {
            return null;
        }
        return UserDTO.builder().build();
    }

    @Override
    public Page<UserDTO> getAll(FilterDTO filterDTO) {
        return null;
    }

    @Override
    public UserDTO update(Long id, UserDTO userDTO) {
        if (id == null) {
            return null;
        }
        return userDTO;
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            return;
        }
    }
}
