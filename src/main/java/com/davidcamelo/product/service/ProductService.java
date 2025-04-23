package com.davidcamelo.product.service;

import com.davidcamelo.product.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO create(ProductDTO productDTO);
    ProductDTO getById(Long id);
    List<ProductDTO> getAll();
    ProductDTO update(Long id, ProductDTO productDTO);
    void delete(Long id);
}
