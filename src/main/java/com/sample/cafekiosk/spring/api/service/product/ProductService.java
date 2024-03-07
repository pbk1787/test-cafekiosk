package com.sample.cafekiosk.spring.api.service.product;

import com.sample.cafekiosk.spring.api.controller.product.dto.request.ProductCreateRequest;
import com.sample.cafekiosk.spring.api.service.product.response.ProductResponse;
import com.sample.cafekiosk.spring.domain.product.Product;
import com.sample.cafekiosk.spring.domain.product.ProductRepository;
import com.sample.cafekiosk.spring.domain.product.ProductSellingStatus;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductResponse> getSellingProducts() {
        List<Product> products = productRepository.findAllBySellingStatusIn(ProductSellingStatus.forDisplay());
        return products.stream()
            .map(ProductResponse::of)
            .toList();
    }

    public ProductResponse createProduct(ProductCreateRequest productCreateRequest) {
        //ProductNumber: DB에서 마지막 저장된 Product의 상품 번호를 읽어와서 +1
        String latestProductNumber = productRepository.findLatestProductNumber();
        return null;
    }
}
