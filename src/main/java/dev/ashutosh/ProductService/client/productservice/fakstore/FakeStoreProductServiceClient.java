package dev.ashutosh.ProductService.client.productservice.fakstore;

import dev.ashutosh.ProductService.dtos.GenericProductDto;
import dev.ashutosh.ProductService.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FakeStoreProductServiceClient  {
    private RestTemplateBuilder restTemplateBuilder;

//    @Value("${fakestore.api.url}")
//    private String fakestoreApiUrl;
//
//    @Value("${fakestore.api.product.path.url}")
//    private String fakestoreProductPathUrl;

    private String createProductRequest;
    private String getProductByIdRequest;
    public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder, @Value("${fakeStore.api.url}") String fakestoreApiUrl,
                @Value("${fakeStore.api.paths.products}") String fakestoreProductPathUrl) {
        this.createProductRequest = fakestoreApiUrl + fakestoreProductPathUrl;
        this.getProductByIdRequest = fakestoreApiUrl + fakestoreProductPathUrl + "/{id}";
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public FakeStoreProductDto createProduct(GenericProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(createProductRequest, product, FakeStoreProductDto.class);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return fakeStoreProductDto;
    }


    public FakeStoreProductDto getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(getProductByIdRequest, FakeStoreProductDto.class, id);
        System.out.println(response.getBody());
        FakeStoreProductDto fakeStoreProductDto = response.getBody();

        if(fakeStoreProductDto == null){
            throw new NotFoundException("Product with id: " + id + " not found");
        }
        return fakeStoreProductDto;
    }


    public List<FakeStoreProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response= restTemplate.getForEntity(createProductRequest, FakeStoreProductDto[].class);
        //FakeStoreProductDto[] fakeStoreProductDtos = response.getBody();
        List<GenericProductDto> products = new ArrayList<>();
        return Arrays.stream(response.getBody()).toList();
    }


    public FakeStoreProductDto deleteProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        //restTemplate.delete(getProductByIdRequest, id);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(
                getProductByIdRequest,
                HttpMethod.DELETE,
                null,  // Request entity (none needed for DELETE)
                FakeStoreProductDto.class,  // Expected response type
                id
        );
        return response.getBody();
    }

    public FakeStoreProductDto updateProductById(Long id, GenericProductDto product) {
        //String updateProductByIdRequest = "https://fakestoreapi.com/products/{id}";
        RestTemplate restTemplate = restTemplateBuilder.build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<GenericProductDto> requestEntity = new HttpEntity<>(product, headers);


        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.exchange(
                getProductByIdRequest,
                HttpMethod.PUT,
                requestEntity,
                FakeStoreProductDto.class,
                id
        );
        return responseEntity.getBody();
    }
}
