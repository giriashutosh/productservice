package dev.ashutosh.ProductService.repositories;

import dev.ashutosh.ProductService.dtos.GenericProductDto;
import dev.ashutosh.ProductService.models.Category;
import dev.ashutosh.ProductService.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findAllByTitle(String title);

    @Query("SELECT p FROM Product p WHERE p.uuid = :id")
    Product findByIdCustom(@Param("id") UUID id);

    @Modifying
    void deleteById(@Param("id") UUID id);

    List<Product> findByCategory(Category category);

}
