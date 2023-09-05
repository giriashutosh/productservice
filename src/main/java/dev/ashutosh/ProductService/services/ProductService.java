package dev.ashutosh.ProductService.services;

import dev.ashutosh.ProductService.dtos.GenericProductDto;

import java.util.List;

public interface ProductService {
  public GenericProductDto createProduct(GenericProductDto product);
  public GenericProductDto getProductById(Long id);
  public List<GenericProductDto> getAllProducts();
    public GenericProductDto deleteProductById(Long id);
}
