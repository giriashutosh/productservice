package dev.ashutosh.ProductService.services;

import dev.ashutosh.ProductService.dtos.GenericProductDto;

public interface ProductService {
  public GenericProductDto createProduct(GenericProductDto product);
  public GenericProductDto getProductById(Long id);
}
