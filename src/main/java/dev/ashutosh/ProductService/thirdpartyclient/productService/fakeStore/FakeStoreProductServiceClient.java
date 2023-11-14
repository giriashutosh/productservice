package dev.ashutosh.ProductService.thirdpartyclient.productService.fakeStore;

import dev.ashutosh.ProductService.dtos.GenericProductDto;
import dev.ashutosh.ProductService.exceptions.NotFoundException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FakeStoreProductServiceClient {
    private RestTemplateBuilder restTemplateBuilder;
    private String getProductByIdRequest = "https://fakestoreapi.com/products/{id}";
    private String createProductRequest = "https://fakestoreapi.com/products";


    public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder){
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
//        for(FakeStoreProductDto fakeStoreProductDto: fakeStoreProductDtos){
//
//            products.add(convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductDto));
//        }
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
