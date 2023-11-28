package dev.ashutosh.ProductService.services;

import dev.ashutosh.ProductService.dtos.GenericProductDto;
import dev.ashutosh.ProductService.exceptions.NotFoundException;
import dev.ashutosh.ProductService.models.Category;
import dev.ashutosh.ProductService.models.Price;
import dev.ashutosh.ProductService.models.Product;
import dev.ashutosh.ProductService.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("selfProductService")
public class SelfProductService implements ProductService {
    private ProductRepository productRepository;

    public SelfProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        Category category = new Category();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        product.setDescription(productDto.getDescription());
        product.setImage(productDto.getImage());
        Price price = new Price();
        price.setPrice(productDto.getPrice());
        product.setPrice(price);
        System.out.println(product);
        Product savedProduct = productRepository.save(product);
        System.out.println(savedProduct);
        return null;
    }

    @Override
    public GenericProductDto getProductById(UUID id) throws NotFoundException {
        System.out.println(id);
        Product getProduct = productRepository.findByIdCustom(id);
        GenericProductDto productDto = new GenericProductDto();
        productDto.setId(id);
        productDto.setTitle(getProduct.getTitle());
        productDto.setCategory(getProduct.getCategory().getName());
        productDto.setDescription(getProduct.getDescription());
        productDto.setImage(getProduct.getImage());
        productDto.setPrice(getProduct.getPrice().getPrice());

        //System.out.println(getProduct.getTitle());
        return productDto;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        List <GenericProductDto> genericProductDtos = new ArrayList<>();
        for (Product product: allProducts)
        {
            GenericProductDto productDto = new GenericProductDto();
            productDto.setTitle(product.getTitle());
            productDto.setCategory(product.getCategory().getName());
            productDto.setDescription(product.getDescription());
            productDto.setImage(product.getImage());
            productDto.setPrice(product.getPrice().getPrice());
            genericProductDtos.add(productDto);
        }
        return genericProductDtos;
    }

    @Override
    public GenericProductDto deleteProductById(Long id) {
        return null;
    }

    @Override
    public GenericProductDto updateProductById(Long id, GenericProductDto product) {
        return null;
    }

    @Override
    public GenericProductDto getAllCategories() {
        return null;
    }

    @Override
    public GenericProductDto getSpecificCategory(String category) {
        return null;
    }
}
