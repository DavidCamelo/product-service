package com.davidcamelo.product.service.impl;

import com.davidcamelo.product.dto.ErrorDTO;
import com.davidcamelo.product.dto.ProductDTO;
import com.davidcamelo.product.dto.UserDTO;
import com.davidcamelo.product.entity.Product;
import com.davidcamelo.product.error.ProductException;
import com.davidcamelo.product.repository.ProductRepository;
import com.davidcamelo.product.service.ProductService;
import com.davidcamelo.product.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final UserService userService;
    private final ProductRepository productRepository;

    @Override
    public ProductDTO create(ProductDTO productDTO) {
        return upsert(productDTO, new Product());
    }

    @Override
    public ProductDTO getById(Long id) {
        return map(findById(id));
    }

    @Override
    public List<ProductDTO> getAll() {
        return productRepository.findAll().stream().map(this::map).toList();
    }

    @Override
    public ProductDTO update(Long id, ProductDTO productDTO) {
        return upsert(productDTO, findById(id));
    }

    @Override
    public void delete(Long id) {
        productRepository.delete(findById(id));
    }

    private ProductDTO upsert(ProductDTO productDTO, Product product) {
        map(productDTO, product);
        return map(productRepository.save(product));
    }

    private Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductException(ErrorDTO.builder().message(String.format("Product with id %s not found", id)).timestamp(new Date()).build()));
    }

    private ProductDTO map(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .user(userService.getById(product.getUserId()))
                .name(product.getName())
                .description(product.getDescription())
                .build();
    }

    private void map(ProductDTO productDTO, Product product) {
        product.setUserId(Optional.ofNullable(productDTO.user()).map(UserDTO::id).orElse(null));
        product.setName(productDTO.name());
        product.setDescription(productDTO.description());
    }
}
