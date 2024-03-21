package com.sample.cafekiosk.spring.api.service.product;

import static com.sample.cafekiosk.spring.domain.product.ProductSellingStatus.SELLING;
import static com.sample.cafekiosk.spring.domain.product.ProductType.HANDMADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import com.sample.cafekiosk.spring.IntegrationTestSupport;
import com.sample.cafekiosk.spring.api.controller.product.request.ProductCreateRequest;
import com.sample.cafekiosk.spring.api.service.product.response.ProductResponse;
import com.sample.cafekiosk.spring.domain.product.Product;
import com.sample.cafekiosk.spring.domain.product.ProductRepository;
import com.sample.cafekiosk.spring.domain.product.ProductSellingStatus;
import com.sample.cafekiosk.spring.domain.product.ProductType;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class ProductServiceTest extends IntegrationTestSupport {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @BeforeAll
    static void beforeAll() {
        // 테스트 클래스 전체 실행 전에 한번 실행됨
    }

    @BeforeEach
    void setUp() {
        //매 테스트 메소드 실행 전에 작업
        //공유 변수를 사용하게되어 사용을 지양하는 것이 좋다
        //문서로서의 역할을 하기가 어려워짐

        //각 테스트 입장에서 봤을 때: 아예 몰라도 테스트 내용을 이해하는데 문제가 없는가?
        //                      수정해도 모든 테스트에 영향을 주지 않는가?
        // 위 두 가지에 속한다면 beforeEach를 사용해도 좋음
    }

    @AfterAll
    static void afterAll() {
        //테스트 클래스 전체가 끝나고 한번 실행됨
    }

    @AfterEach
    void tearDown() {
        //매 테스트 메소드가 실행 된 후에 작업
        productRepository.deleteAllInBatch();
    }

    @DisplayName("신규 상품을 등록한다. 상품번호는 가장 최근 상품의 상품번호에서 1 증가한 값이다")
    @Test
    void createProduct() {
        //given
        Product product = createProduct("001", HANDMADE, SELLING, "아메리카노", 4000);
        productRepository.save(product);

        ProductCreateRequest request = ProductCreateRequest.builder()
            .type(HANDMADE)
            .sellingStatus(SELLING)
            .name("카푸치노")
            .price(5000)
            .build();

        //when
        ProductResponse productResponse = productService.createProduct(request.toServiceRequest());

        //then
        assertThat(productResponse)
            .extracting("productNumber", "type", "sellingStatus", "name", "price")
            .contains("002", HANDMADE, SELLING, "카푸치노", 5000);

        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(2)
            .extracting("productNumber", "type", "sellingStatus", "name", "price")
            .containsExactlyInAnyOrder(
                tuple("001", HANDMADE, SELLING, "아메리카노", 4000),
                tuple("002", HANDMADE, SELLING, "카푸치노", 5000)
            );
    }

    @DisplayName("상품이 하나도 없는 경우 신규 상품을 등록하면 상품번호는 001이다")
    @Test
    void createProductWhenProductsIsEmpty() {
        //given
        ProductCreateRequest request = ProductCreateRequest.builder()
            .type(HANDMADE)
            .sellingStatus(SELLING)
            .name("카푸치노")
            .price(5000)
            .build();

        //when
        ProductResponse productResponse = productService.createProduct(request.toServiceRequest());

        //then
        assertThat(productResponse)
            .extracting("productNumber", "type", "sellingStatus", "name", "price")
            .contains("001", HANDMADE, SELLING, "카푸치노", 5000);

        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(1)
            .extracting("productNumber", "type", "sellingStatus", "name", "price")
            .contains(
                tuple("001", HANDMADE, SELLING, "카푸치노", 5000)
            );
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