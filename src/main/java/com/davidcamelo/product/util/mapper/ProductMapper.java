package com.davidcamelo.product.util.mapper;

import com.davidcamelo.product.dto.ProductDTO;
import com.davidcamelo.product.entity.Product;
import org.springframework.data.domain.Page;

public interface ProductMapper {
    ProductDTO map(Product product);
    void map(ProductDTO productDTO, Product product);
    Page<ProductDTO> mapPage(Page<Product> all);
}
