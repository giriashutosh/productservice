package dev.ashutosh.ProductService.services;

import dev.ashutosh.ProductService.dtos.GenericProductDto;
import dev.ashutosh.ProductService.exceptions.NotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface ProductService {
  public GenericProductDto createProduct(GenericProductDto product);
  public GenericProductDto getProductById(UUID id) throws NotFoundException;
  public List<GenericProductDto> getAllProducts();
  public GenericProductDto deleteProductById(Long id);
  public GenericProductDto updateProductById(Long id, GenericProductDto product);
  public GenericProductDto getAllCategories();
    public GenericProductDto getSpecificCategory(String category);
}
