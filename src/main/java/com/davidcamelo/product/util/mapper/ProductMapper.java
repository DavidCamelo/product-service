package com.davidcamelo.product.util.mapper;

import com.davidcamelo.product.dto.ProductDTO;
import com.davidcamelo.product.entity.Product;

public interface ProductMapper {
    ProductDTO map(Product product);
    void map(ProductDTO productDTO, Product product);
}
