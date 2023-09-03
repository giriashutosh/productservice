package dev.ashutosh.ProductService.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public void getAllProducts(){

    }

    @GetMapping("{id}")
    public String getProductById(@PathVariable("id") Long id){
        return "Product Id is: " + id;
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
