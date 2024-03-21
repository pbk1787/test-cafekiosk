package com.sample.cafekiosk.spring.api.service.product;

import static com.sample.cafekiosk.spring.domain.product.ProductSellingStatus.SELLING;
import static com.sample.cafekiosk.spring.domain.product.ProductType.HANDMADE;
import static org.assertj.core.api.Assertions.assertThat;

import com.sample.cafekiosk.spring.IntegrationTestSupport;
import com.sample.cafekiosk.spring.domain.product.Product;
import com.sample.cafekiosk.spring.domain.product.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
class ProductNumberFactoryTest extends IntegrationTestSupport {

    @Autowired
    private ProductNumberFactory productNumberFactory;

    @Autowired
    private ProductRepository productRepository;

    @DisplayName("상품 번호를 가져온다. 상품 번호는 가장 최근 상품의 상품번호에서 1 증가한 값이다.")
    @Test
    void createNextProductNumber() {
        //given
        Product product = createProduct("001");
        productRepository.save(product);

        //when
        String result = productNumberFactory.createNextProductNumber();

        //then
        assertThat(result).isEqualTo("002");
    }

    @DisplayName("상품이 하나도 없는 경우 신규 상품을 등록하면 상품번호는 001이다.")
    @Test
    void createNextProductNumberWhenWithoutProduct() {
        //given
        //when
        String result = productNumberFactory.createNextProductNumber();

        //then
        assertThat(result).isEqualTo("001");

    }

    private static Product createProduct(String productNumber) {
        return Product.builder()
            .productNumber(productNumber)
            .type(HANDMADE)
            .sellingStatus(SELLING)
            .name("아메리카노")
            .price(4000)
            .build();
    }
}