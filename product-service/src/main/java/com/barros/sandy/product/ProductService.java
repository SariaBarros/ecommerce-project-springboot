package com.barros.sandy.product;

import com.barros.sandy.product.dto.ProductRequest;
import com.barros.sandy.product.dto.ProductResponse;
import com.barros.sandy.product.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public void addProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .description(productRequest.getDescription())
                .pictureUrl(productRequest.getPictureUrl())
                .build();
        productRepository.save(product);
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = (List<Product>) productRepository.findAll();

        List<ProductResponse> productResponseList = products.stream().map(product -> new ProductResponse().builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .pictureUrl(product.getPictureUrl())
                .build()).toList();

        return productResponseList;
    }
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id).get();
        ProductResponse productResponse = new ProductResponse().ofProduct(product);
        return productResponse;
    }
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public void updateProduct(ProductRequest productRequest, Long id) {
         productRepository.findById(id).map(
                product -> {
                    product.setName(productRequest.getName());
                    product.setPrice(productRequest.getPrice());
                    product.setDescription(productRequest.getDescription());
                    product.setPictureUrl(productRequest.getPictureUrl());
                    return productRepository.save(product);
                }).orElseThrow(IllegalArgumentException::new);
    }
}
