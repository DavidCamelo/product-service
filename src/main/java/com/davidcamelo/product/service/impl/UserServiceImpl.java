package com.davidcamelo.product.service.impl;

import com.davidcamelo.product.dto.FilterDTO;
import com.davidcamelo.product.dto.UserDTO;
import com.davidcamelo.product.service.UserService;
import com.davidcamelo.product.util.http.UserClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserClient userClient;

    @Override
    public UserDTO create(UserDTO userDTO) {
        try {
            return userClient.create(userDTO);
        } catch (Exception e) {
            log.error("Error creating user: {}", userDTO, e);
        }
        return null;
    }

    @Override
    public UserDTO getById(Long id) {
        try {
            return userClient.getById(id);
        } catch (Exception e) {
            log.error("User not found with id: {}", id, e);
        }
        return null;
    }

    @Override
    public Page<UserDTO> getAll(FilterDTO filterDTO) {
        try {
            return userClient.getAll(filterDTO);
        } catch (Exception e) {
            log.error("Error getting users: {}", filterDTO, e);
        }
        return null;
    }

    @Override
    public UserDTO update(Long id, UserDTO userDTO) {
        try {
            return userClient.update(id, userDTO);
        } catch (Exception e) {
            log.error("Error updating user: {}", userDTO, e);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        try {
            userClient.delete(id);
        } catch (Exception e) {
            log.error("Error deleting user with id: {}", id, e);
        }
    }
}
