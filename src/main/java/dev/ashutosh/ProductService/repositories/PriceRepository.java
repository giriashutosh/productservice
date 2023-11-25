package dev.ashutosh.ProductService.repositories;

import dev.ashutosh.ProductService.models.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Long> {
}
