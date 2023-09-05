package dev.ashutosh.ProductService.services;

import dev.ashutosh.ProductService.dtos.GenericProductDto;
import dev.ashutosh.ProductService.exceptions.NotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
  public GenericProductDto createProduct(GenericProductDto product);
  public GenericProductDto getProductById(Long id) throws NotFoundException;
  public List<GenericProductDto> getAllProducts();
    public GenericProductDto deleteProductById(Long id);
    public GenericProductDto updateProductById(Long id, GenericProductDto product);
}
