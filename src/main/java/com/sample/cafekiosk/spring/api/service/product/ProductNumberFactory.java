package com.sample.cafekiosk.spring.api.service.product;

import com.sample.cafekiosk.spring.domain.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductNumberFactory {

    private final ProductRepository productRepository;

    /**
     * DB에서 마지막 저장된 Product의 상품 번호를 읽어와서 +1
     *
     * @return
     */
    public String createNextProductNumber() {
        String latestProductNumber = productRepository.findLatestProductNumber();
        if (latestProductNumber == null) {
            return "001";
        }

        int latestProductNumberInt = Integer.parseInt(latestProductNumber);
        int nextProductNumberInt = latestProductNumberInt + 1;

        // 9 -> 009 , 10 -> 010
        return String.format("%03d", nextProductNumberInt);
    }

}
