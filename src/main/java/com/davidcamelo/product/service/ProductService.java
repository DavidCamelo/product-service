package com.davidcamelo.product.service;

import com.davidcamelo.product.dto.FilterDTO;
import com.davidcamelo.product.dto.ProductDTO;
import org.springframework.data.domain.Page;

public interface ProductService {
    ProductDTO create(ProductDTO productDTO);
    ProductDTO getById(Long id);
    Page<ProductDTO> getAll(FilterDTO filterDTO);
    ProductDTO update(Long id, ProductDTO productDTO);
    void delete(Long id);
}
