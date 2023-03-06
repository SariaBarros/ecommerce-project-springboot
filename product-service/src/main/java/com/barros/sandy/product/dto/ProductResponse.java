package com.barros.sandy.product.dto;

import com.barros.sandy.product.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private String pictureUrl;

    public Product toProduct(ProductResponse productResponse){
      Product product = Product.builder()
              .id(productResponse.getId())
              .name(productResponse.getName())
              .description(productResponse.getDescription())
              .price(productResponse.getPrice())
              .pictureUrl(productResponse.getPictureUrl()).build();

      return product;
    }

}
