package com.sample.cafekiosk.spring.domain.order;

import static com.sample.cafekiosk.spring.domain.product.ProductSellingStatus.SELLING;
import static com.sample.cafekiosk.spring.domain.product.ProductType.BAKERY;
import static com.sample.cafekiosk.spring.domain.product.ProductType.BOTTLE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import com.sample.cafekiosk.spring.domain.product.Product;
import com.sample.cafekiosk.spring.domain.product.ProductRepository;
import com.sample.cafekiosk.spring.domain.product.ProductType;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @DisplayName("주문 날짜와 상태로 주문 목록을 조회한다")
    @Test
    void findOrderBy() {
        //given
        LocalDateTime registeredDateTime = LocalDateTime.now();
        Product product1 = createProduct(BOTTLE, "001", 1000);
        Product product2 = createProduct(BAKERY, "002", 3000);
        productRepository.saveAll(List.of(product1, product2));

        Order order = Order.builder()
            .orderStatus(OrderStatus.PAYMENT_COMPLETED)
            .products(List.of(product1, product2))
            .registeredDateTime(registeredDateTime)
            .build();
        orderRepository.save(order);

        LocalDateTime startDateTime = LocalDateTime.now().minusDays(1);
        LocalDateTime endDateTime = LocalDateTime.now();

        //when
        List<Order> ordersBy = orderRepository.findOrdersBy(startDateTime, endDateTime, OrderStatus.PAYMENT_COMPLETED);

        //then
        assertThat(ordersBy).hasSize(1)
            .extracting("orderStatus", "totalPrice")
            .containsExactlyInAnyOrder(
                tuple(OrderStatus.PAYMENT_COMPLETED, 4000)
            );

    }

    private Product createProduct(ProductType type, String productNumber, int price) {
        return Product.builder()
            .productNumber(productNumber)
            .type(type)
            .sellingStatus(SELLING)
            .name("메뉴" + productNumber)
            .price(price)
            .build();
    }
}