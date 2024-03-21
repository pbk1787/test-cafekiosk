package com.sample.cafekiosk.spring.domain.stock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

class StockTest {

    @DisplayName("상품 번호와 수량을 입력받아 Stock 객체를 만들 수 있다")
    @Test
    void createStock() {
        //given
        String productNumber = "001";
        int quantity = 1;

        //when
        Stock stock = Stock.create(productNumber, quantity);

        //then
        assertThat(stock.getProductNumber()).isEqualTo(productNumber);
        assertThat(stock.getQuantity()).isEqualTo(quantity);
    }

    @DisplayName("재고의 수량이 제공된(주문된) 수량보다 작은지 확인한다.")
    @Test
    void isQuantityLessThan() {
        //given
        Stock stock = Stock.create("001", 1);
        int quatity = 2;

        //when
        boolean result = stock.isQuantityLessThan(quatity);

        //then
        assertThat(result).isTrue();
    }

    @DisplayName("재고를 주어진 개수만큼 차감할 수 있다.")
    @Test
    void deductQuantity() {
        //given
        Stock stock = Stock.create("001", 1);
        int quatity = 1;

        //when
        stock.deductQuantity(quatity);

        //then
        assertThat(stock.getQuantity()).isZero();

    }

    @DisplayName("재고보다 많은 수의 수량으로 차감 시도를 하는 경우 예외가 발생한다.")
    @Test
    void deductQuantity2() {
        //given
        Stock stock = Stock.create("001", 1);
        int quatity = 2;

        //when
        //then
        assertThatThrownBy(() -> stock.deductQuantity(quatity))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("차감할 재고 수량이 없습니다.");

    }

    @DisplayName("재고 차감 시나리오")
    @TestFactory
    Collection<DynamicTest> stockDeductionDynamicTest() {
        //given
        Stock stock = Stock.create("001", 1);

        return List.of(
            DynamicTest.dynamicTest("재고를 주어진 개수만큼 차감할 수 있다.", () -> {
                //given
                int quantity = 1;

                //when
                stock.deductQuantity(quantity);

                //then
                assertThat(stock.getQuantity()).isZero();
            }),
            DynamicTest.dynamicTest("재고보다 많은 수의 수량으로 차감 시도하는 경우 예외가 발생한다.", () -> {
                //given
                int quantity = 1;

                //when //then
                assertThatThrownBy(() -> stock.deductQuantity(quantity))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("차감할 재고 수량이 없습니다.");
            })
        );
        
    }

}