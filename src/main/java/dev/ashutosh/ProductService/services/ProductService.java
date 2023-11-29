package dev.ashutosh.ProductService.services;

import dev.ashutosh.ProductService.dtos.CategoryDto;
import dev.ashutosh.ProductService.dtos.GenericProductDto;
import dev.ashutosh.ProductService.exceptions.NotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface ProductService {
  public GenericProductDto createProduct(GenericProductDto product);
  public GenericProductDto getProductById(UUID id) throws NotFoundException;
  public List<GenericProductDto> getAllProducts();
  public String deleteProductById(UUID id);
  public GenericProductDto updateProductById(UUID id, GenericProductDto product) throws NotFoundException;
  public List<CategoryDto> getAllCategories();
  public List<GenericProductDto> getInCategory(String category);
}
