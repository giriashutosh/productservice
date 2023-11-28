package dev.ashutosh.ProductService;

import dev.ashutosh.ProductService.models.Category;
import dev.ashutosh.ProductService.models.Price;
import dev.ashutosh.ProductService.models.Product;
import dev.ashutosh.ProductService.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

}
