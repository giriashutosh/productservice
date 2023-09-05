package dev.ashutosh.ProductService.services;

import dev.ashutosh.ProductService.dtos.FakeStoreProductDto;
import dev.ashutosh.ProductService.dtos.GenericProductDto;
import dev.ashutosh.ProductService.exceptions.NotFoundException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private RestTemplateBuilder restTemplateBuilder;
    private String getProductByIdRequest = "https://fakestoreapi.com/products/{id}";
    private String createProductRequest = "https://fakestoreapi.com/products";

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    private GenericProductDto convertFakeStoreProductDtoToGenericProductDto(FakeStoreProductDto fakeStoreProductDto){
        GenericProductDto productDto = new GenericProductDto();
        productDto.setId(fakeStoreProductDto.getId());
        productDto.setCategory(fakeStoreProductDto.getCategory());
        productDto.setPrice(fakeStoreProductDto.getPrice());
        productDto.setTitle(fakeStoreProductDto.getTitle());
        productDto.setImage(fakeStoreProductDto.getImage());
        productDto.setDescription(fakeStoreProductDto.getDescription());
        return productDto;
    }
    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(createProductRequest, product, FakeStoreProductDto.class);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();


        return convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductDto);
    }

    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(getProductByIdRequest, FakeStoreProductDto.class, id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();

        if(fakeStoreProductDto == null){
            throw new NotFoundException("Product with id: " + id + " not found");
        }



        return convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductDto);
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response= restTemplate.getForEntity(createProductRequest, FakeStoreProductDto[].class);
        FakeStoreProductDto[] fakeStoreProductDtos = response.getBody();
        List<GenericProductDto> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto: fakeStoreProductDtos){

            products.add(convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductDto));
        }
        return products;
    }

    @Override
    public GenericProductDto deleteProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        //restTemplate.delete(getProductByIdRequest, id);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(
                getProductByIdRequest,
                HttpMethod.DELETE,
                null,  // Request entity (none needed for DELETE)
                FakeStoreProductDto.class  // Expected response type
        );
        return convertFakeStoreProductDtoToGenericProductDto(response.getBody());
    }

    @Override
    public GenericProductDto updateProductById(Long id, GenericProductDto product) {
        String updateProductByIdRequest = "https://fakestoreapi.com/products/{id}";
        RestTemplate restTemplate = restTemplateBuilder.build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<GenericProductDto> requestEntity = new HttpEntity<>(product, headers);


        ResponseEntity<GenericProductDto> responseEntity = restTemplate.exchange(
                updateProductByIdRequest,
                HttpMethod.PUT,
                requestEntity,
                GenericProductDto.class
        );
        return responseEntity.getBody();
    }
}
