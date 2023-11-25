package dev.ashutosh.ProductService;

import dev.ashutosh.ProductService.models.Category;
import dev.ashutosh.ProductService.models.Price;
import dev.ashutosh.ProductService.models.Product;
import dev.ashutosh.ProductService.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductServiceApplication implements CommandLineRunner {

	private final ProductRepository productRepository;

	public ProductServiceApplication(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		Category category = new Category();
		category.setName("Electronics");

		Price price = new Price();
		price.setPrice(1000);
        price.setCurrency("USD");

		Product product = new Product();
		product.setTitle("iPhone 12");
		product.setDescription("Apple iPhone 12");
		product.setImage("https://www.apple.com/newsroom/images/product/iphone/standard/Apple_new-iphone12-pro-family_10132020_big.jpg.large.jpg");
		product.setCategory(category);
		product.setPrice(price);
		product.setInventoryCount(10);

		productRepository.save(product);

		productRepository.findAllByTitle("iPhone 12").forEach(System.out::println);
	}
}
