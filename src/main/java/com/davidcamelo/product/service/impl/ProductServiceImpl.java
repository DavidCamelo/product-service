package com.davidcamelo.product.service.impl;

import com.davidcamelo.product.dto.ErrorDTO;
import com.davidcamelo.product.dto.FilterDTO;
import com.davidcamelo.product.dto.ProductDTO;
import com.davidcamelo.product.entity.Product;
import com.davidcamelo.product.error.ProductNotFoundException;
import com.davidcamelo.product.repository.ProductRepository;
import com.davidcamelo.product.service.ProductService;
import com.davidcamelo.product.util.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    @Override
    public ProductDTO create(ProductDTO productDTO) {
        return upsert(productDTO, new Product());
    }

    @Override
    public ProductDTO getById(Long id) {
        return productMapper.map(findById(id));
    }

    @Override
    public Page<ProductDTO> getAll(FilterDTO filterDTO) {
        if (filterDTO.getPageNumber() != null && filterDTO.getPageSize() != null) {
            var pageRequest = PageRequest.of(filterDTO.getPageNumber(), filterDTO.getPageSize(), Sort.by(new Sort.Order(filterDTO.getSortDirection(), filterDTO.getSortBy())));
            return productMapper.mapPage(productRepository.findAll(pageRequest));
        }
        var products = productRepository.findAll().stream().map(productMapper::map).toList();
        return new PageImpl<>(products, PageRequest.of(0, products.size()), products.size());
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
        productMapper.map(productDTO, product);
        return productMapper.map(productRepository.save(product));
    }

    private Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(ErrorDTO.builder().message(String.format("Product with id %s not found", id)).timestamp(new Date()).build()));
    }
}
