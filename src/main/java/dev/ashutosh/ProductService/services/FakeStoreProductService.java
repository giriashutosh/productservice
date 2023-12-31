//package dev.ashutosh.ProductService.services;
//
//import dev.ashutosh.ProductService.client.productservice.fakstore.FakeStoreProductDto;
//import dev.ashutosh.ProductService.dtos.GenericProductDto;
//import dev.ashutosh.ProductService.exceptions.NotFoundException;
//import dev.ashutosh.ProductService.client.productservice.fakstore.FakeStoreProductServiceClient;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//@Service("fakeStoreProductService")
//public class FakeStoreProductService implements ProductService{
//
//    private FakeStoreProductServiceClient fakeStoreProductServiceClient;
//
//    public FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient){
//        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
//    }
//
//    private GenericProductDto convertFakeStoreProductDtoToGenericProductDto(FakeStoreProductDto fakeStoreProductDto){
//        GenericProductDto productDto = new GenericProductDto();
//        //productDto.setId(fakeStoreProductDto.getId());
//        //productDto.setCategory(fakeStoreProductDto.getCategory());
//        //productDto.setPrice(fakeStoreProductDto.getPrice());
//        productDto.setTitle(fakeStoreProductDto.getTitle());
//        productDto.setImage(fakeStoreProductDto.getImage());
//        productDto.setDescription(fakeStoreProductDto.getDescription());
//        return productDto;
//    }
//    @Override
//    public GenericProductDto createProduct(GenericProductDto product) {
//
//        return convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductServiceClient.createProduct(product));
//    }
//
//    @Override
//    public GenericProductDto getProductById(UUID id) throws NotFoundException {
//
//        return convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductServiceClient.getProductById(id));
//    }
//
//    @Override
//    public List<GenericProductDto> getAllProducts() {
//        List<FakeStoreProductDto> fakeStoreProductDtos = fakeStoreProductServiceClient.getAllProducts();
//        List<GenericProductDto> products = new ArrayList<>();
//        for(FakeStoreProductDto fakeStoreProductDto: fakeStoreProductDtos){
//
//            products.add(convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductDto));
//        }
//        return products;
//    }
//
//    @Override
//    public GenericProductDto deleteProductById(UUID id) {
//        return convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductServiceClient.deleteProductById(id));
//
//    }
//
//    @Override
//    public GenericProductDto updateProductById(Long id, GenericProductDto product) {
//        System.out.println("Updated successfully");
//        return convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductServiceClient.updateProductById(id, product));
//    }
//
//    @Override
//    public GenericProductDto getAllCategories() {
//        return null;
//    }
//
//    @Override
//    public GenericProductDto getSpecificCategory(String category) {
//        return null;
//    }
//}
