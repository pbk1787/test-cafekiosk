package com.sample.cafekiosk.spring.api.service.product;

import com.sample.cafekiosk.spring.api.service.product.request.ProductCreateServiceRequest;
import com.sample.cafekiosk.spring.api.service.product.response.ProductResponse;
import com.sample.cafekiosk.spring.domain.product.Product;
import com.sample.cafekiosk.spring.domain.product.ProductRepository;
import com.sample.cafekiosk.spring.domain.product.ProductSellingStatus;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductNumberFactory productNumberFactory;

    public List<ProductResponse> getSellingProducts() {
        List<Product> products = productRepository.findAllBySellingStatusIn(ProductSellingStatus.forDisplay());
        return products.stream()
            .map(ProductResponse::of)
            .toList();
    }

    @Transactional
    public ProductResponse createProduct(ProductCreateServiceRequest productCreateRequest) {
        String nextProductNumber = productNumberFactory.createNextProductNumber();

        Product product = productCreateRequest.toEntity(nextProductNumber);
        Product savedProduct = productRepository.save(product);

        return ProductResponse.of(savedProduct);
    }

}
