package dev.ashutosh.ProductService.services;

import dev.ashutosh.ProductService.dtos.CategoryDto;
import dev.ashutosh.ProductService.dtos.GenericProductDto;
import dev.ashutosh.ProductService.exceptions.NotFoundException;
import dev.ashutosh.ProductService.models.Category;
import dev.ashutosh.ProductService.models.Price;
import dev.ashutosh.ProductService.models.Product;
import dev.ashutosh.ProductService.repositories.CategoryRepository;
import dev.ashutosh.ProductService.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.context.aot.ApplicationContextAotGenerator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service("selfProductService")
public class SelfProductService implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    private GenericProductDto convertToDto(Product product) {
        GenericProductDto productDto = new GenericProductDto();
        productDto.setTitle(product.getTitle());
        productDto.setCategory(product.getCategory().getName());
        productDto.setDescription(product.getDescription());
        productDto.setImage(product.getImage());
        productDto.setPrice(product.getPrice().getPrice());
        return productDto;
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
    public String deleteProductById(UUID id) {
        productRepository.deleteById(id);
        return "Product with id: " + id + " deleted successfully";
    }

    @Override
    public GenericProductDto updateProductById(UUID id, GenericProductDto product) throws NotFoundException {
        Product getProduct = productRepository.findByIdCustom(id);
        if(getProduct == null){
            throw new NotFoundException("Product with id: " + id + " not found");
        }
        BeanUtils.copyProperties(product, getProduct, "uuid");
        productRepository.save(getProduct);
        GenericProductDto productDto = new GenericProductDto();

        productDto.setTitle(getProduct.getTitle());
        productDto.setCategory(getProduct.getCategory().getName());
        productDto.setDescription(getProduct.getDescription());
        productDto.setImage(getProduct.getImage());
        productDto.setPrice(getProduct.getPrice().getPrice());
        return productDto;

    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> category = categoryRepository.findAll();
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for (Category category1: category){
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setName(category1.getName());
            categoryDtos.add(categoryDto);
        }
        return categoryDtos;

    }

    @Override
    public List<GenericProductDto> getInCategory(String categoryName) {
        Category category = categoryRepository.findByName(categoryName);
        if (category == null) {
            // Handle the case where the category doesn't exist
            return Collections.emptyList(); // or throw an exception, return a specific DTO, etc.
        }

        // Retrieve the list of products in the specified category
        List<Product> productsInCategory = productRepository.findByCategory(category);

        // Convert the list of products to a list of DTOs
        return productsInCategory.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

}
