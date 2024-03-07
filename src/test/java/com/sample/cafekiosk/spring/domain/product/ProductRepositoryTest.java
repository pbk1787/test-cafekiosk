package com.sample.cafekiosk.spring.domain.product;

import static com.sample.cafekiosk.spring.domain.product.ProductSellingStatus.HOLD;
import static com.sample.cafekiosk.spring.domain.product.ProductSellingStatus.SELLING;
import static com.sample.cafekiosk.spring.domain.product.ProductSellingStatus.STOP_SELLING;
import static com.sample.cafekiosk.spring.domain.product.ProductType.HANDMADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@DataJpaTest
class ProductRepositoryTest {

    /**
     * 내가 작성한 코드로 원하는 쿼리가 제대로 만들어지는지, 앞으로 어떤 형태로 변형될지 모르기 때문에 ...
     * <p>
     * JPA의 Query method를 사용하거나, 다양한 구현 (ex. JPQL, QueryDSL, mybatis)을 사용하여 원하는 쿼리의 형태가 보장이 되지 않을 수 있기 때문에 Repository 테스트를 작성하는 것이 좋다.
     */

    @Autowired
    private ProductRepository productRepository;


    @DisplayName("원하는 판매상태를 가진 상품들을 조회한다")
    @Test
    void findAllBySellingStatusIn() {
        //given
        Product product1 = createProduct("001", HANDMADE, SELLING, "아메리카노", 4000);
        Product product2 = createProduct("002", HANDMADE, HOLD, "카페라떼", 4500);
        Product product3 = createProduct("003", HANDMADE, STOP_SELLING, "팥빙수", 7000);
        productRepository.saveAll(List.of(product1, product2, product3));

        //when
        List<Product> products = productRepository.findAllBySellingStatusIn(List.of(SELLING, HOLD));

        //then
        assertThat(products).hasSize(2)
            .extracting("productNumber", "name", "sellingStatus")
            .containsExactlyInAnyOrder(
                tuple("001", "아메리카노", SELLING),
                tuple("002", "카페라떼", HOLD)
            );

    }

    @DisplayName("상품번호 리스트로 상품들을 조회한다")
    @Test
    void findAllByProductNumberIn() {
        //given
        Product product1 = createProduct("001", HANDMADE, SELLING, "아메리카노", 4000);
        Product product2 = createProduct("002", HANDMADE, HOLD, "카페라떼", 4500);
        Product product3 = createProduct("003", HANDMADE, STOP_SELLING, "팥빙수", 7000);
        productRepository.saveAll(List.of(product1, product2, product3));

        //when
        List<Product> products = productRepository.findAllByProductNumberIn(List.of("001", "002"));

        //then
        assertThat(products).hasSize(2)
            .extracting("productNumber", "name", "sellingStatus")
            .containsExactlyInAnyOrder(
                tuple("001", "아메리카노", SELLING),
                tuple("002", "카페라떼", HOLD)
            );

    }

    @DisplayName("가장 마지막으로 저장한 상품의 상품 번호를 읽어온다.")
    @Test
    void findLatestProductNumber() {
        //given
        String targetProductNumber = "003";
        Product product1 = createProduct("001", HANDMADE, SELLING, "아메리카노", 4000);
        Product product2 = createProduct("002", HANDMADE, HOLD, "카페라떼", 4500);
        Product product3 = createProduct(targetProductNumber, HANDMADE, SELLING, "아메리카노", 4000);
        productRepository.saveAll(List.of(product1, product2, product3));

        //when
        String latestProductNumber = productRepository.findLatestProductNumber();

        //then
        assertThat(latestProductNumber).isEqualTo(targetProductNumber);

    }

    @DisplayName("가장 마지막으로 저장한 상품의 상품 번호를 읽어올 때, 상품이 하나도 없는 경우에는 null을 반환한다")
    @Test
    void findLatestProductNumberWhenProductIsEmpty() {
        //given
        //when
        String latestProductNumber = productRepository.findLatestProductNumber();

        //then
        assertThat(latestProductNumber).isNull();

    }

    private static Product createProduct(String productNumber, ProductType productType, ProductSellingStatus sellingStatus, String name, int price) {
        return Product.builder()
            .productNumber(productNumber)
            .type(productType)
            .sellingStatus(sellingStatus)
            .name(name)
            .price(price)
            .build();
    }


}