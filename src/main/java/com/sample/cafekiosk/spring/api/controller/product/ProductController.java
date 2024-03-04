package com.sample.cafekiosk.spring.api.controller.product;

import com.sample.cafekiosk.spring.api.service.product.ProductService;
import com.sample.cafekiosk.spring.api.service.product.response.ProductResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products/selling")
    public List<ProductResponse> getSellingProduct() {
        return productService.getSellingProducts();
    }

}
