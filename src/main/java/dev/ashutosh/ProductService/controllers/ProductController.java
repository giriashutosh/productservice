package dev.ashutosh.ProductService.controllers;

import dev.ashutosh.ProductService.dtos.CategoryDto;
import dev.ashutosh.ProductService.dtos.GenericProductDto;
import dev.ashutosh.ProductService.exceptions.NotFoundException;
import dev.ashutosh.ProductService.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(@Qualifier("selfProductService") ProductService productService){
        this.productService = productService;
    }
    @GetMapping
    public List<GenericProductDto> getAllProducts(){
       return  productService.getAllProducts();
    }

    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") UUID id) throws NotFoundException {
        return productService.getProductById(id);
    }

    @DeleteMapping("{id}")
    public String deleteProductById(@PathVariable("id") UUID id){

       return productService.deleteProductById(id);
    }
    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto product){
        System.out.println(product);
        return productService.createProduct(product);
    }

    @PutMapping("{id}")
    public GenericProductDto updateProductById(@PathVariable("id") UUID id, @RequestBody GenericProductDto product) {
        GenericProductDto updatedProduct = new GenericProductDto();
        try{
            updatedProduct = productService.updateProductById(id, product);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return updatedProduct;
    }

    @GetMapping("/categories")
    public List<CategoryDto> getAllCategories(){
        return productService.getAllCategories();
    }

    @GetMapping("/categories/{category}")
    public List<GenericProductDto> getInCategory(@PathVariable("category") String category){
        return productService.getInCategory(category);
    }
}
