package dev.ashutosh.ProductService.controllers;

import dev.ashutosh.ProductService.dtos.GenericProductDto;
import dev.ashutosh.ProductService.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService){
        this.productService = productService;
    }
    @GetMapping
    public void getAllProducts(){

    }

    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id){
        return productService.getProductById(id);
    }

    @DeleteMapping("{id}")
    public void deleteProductById(){

    }
    @PostMapping
    public String createProduct(){
        return "created a product with id: " + UUID.randomUUID();
    }

    @PutMapping("{id}")
    public void updateProductById(){

    }
}
