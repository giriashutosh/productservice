package dev.ashutosh.ProductService.client.productservice.fakstore;

import dev.ashutosh.ProductService.models.Category;
import dev.ashutosh.ProductService.models.Price;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private String description;
    private String image;
    private Category category;
    private Price price;
}
