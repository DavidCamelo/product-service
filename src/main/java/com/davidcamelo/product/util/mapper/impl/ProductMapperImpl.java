package com.davidcamelo.product.util.mapper.impl;

import com.davidcamelo.product.dto.ProductDTO;
import com.davidcamelo.product.dto.UserDTO;
import com.davidcamelo.product.entity.Product;
import com.davidcamelo.product.service.UserService;
import com.davidcamelo.product.util.http.UserClient;
import com.davidcamelo.product.util.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductMapperImpl implements ProductMapper {
    private final UserService userService;

    @Override
    public ProductDTO map(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .user(userService.getById(product.getUserId()))
                .name(product.getName())
                .description(product.getDescription())
                .build();
    }

    @Override
    public void map(ProductDTO productDTO, Product product) {
        product.setUserId(Optional.ofNullable(productDTO.user()).map(UserDTO::id).orElse(null));
        product.setName(productDTO.name());
        product.setDescription(productDTO.description());
    }

    @Override
    public Page<ProductDTO> mapPage(Page<Product> productPage) {
        return productPage.map(this::map);
    }
}
