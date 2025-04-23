package com.davidcamelo.product.service;

import com.davidcamelo.product.dto.UserDTO;

public interface UserService {
    UserDTO getById(Long id);
}
