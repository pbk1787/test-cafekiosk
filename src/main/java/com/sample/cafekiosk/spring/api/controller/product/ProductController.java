package com.sample.cafekiosk.spring.api.controller.product;

import com.sample.cafekiosk.spring.api.controller.product.dto.request.ProductCreateRequest;
import com.sample.cafekiosk.spring.api.service.product.ProductService;
import com.sample.cafekiosk.spring.api.service.product.response.ProductResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/selling")
    public List<ProductResponse> getSellingProduct() {
        return productService.getSellingProducts();
    }

    @PostMapping("/new")
    public void createProduct(@RequestBody ProductCreateRequest productCreateRequest) {
        productService.createProduct(productCreateRequest);
    }

}
