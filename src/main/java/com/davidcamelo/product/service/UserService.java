package com.davidcamelo.product.service;

import com.davidcamelo.product.dto.FilterDTO;
import com.davidcamelo.product.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;

//@HttpExchange("api/users")
public interface UserService {
    //@PostExchange
    UserDTO create(/*@RequestBody */UserDTO userDTO);
    //@GetExchange("/{id}")
    UserDTO getById(/*@PathVariable */Long id);
    //@GetExchange
    Page<UserDTO> getAll(/*@ModelAttribute */FilterDTO filterDTO);
    //@PutExchange("/{id}")
    UserDTO update(/*@PathVariable */Long id, /*@RequestBody */UserDTO userDTO);
    //@DeleteExchange("/{id}")
    void delete(/*@PathVariable */Long id);
}
