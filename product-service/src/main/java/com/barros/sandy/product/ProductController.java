package com.barros.sandy.product;

import com.barros.sandy.product.dto.ProductRequest;
import com.barros.sandy.product.dto.ProductResponse;
import com.barros.sandy.product.model.Product;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@Data
public class ProductController {
    private final ProductService productService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(@RequestBody ProductRequest productRequest){
         productService.addProduct(productRequest);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }
}
