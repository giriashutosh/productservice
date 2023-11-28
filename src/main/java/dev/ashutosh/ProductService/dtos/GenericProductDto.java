package dev.ashutosh.ProductService.dtos;

import dev.ashutosh.ProductService.models.Category;
import dev.ashutosh.ProductService.models.Price;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class GenericProductDto {
    private UUID id;
    private String title;
    private String description;
    private String image;
    private String category;
    private Double price;
}
