package com.sample.cafekiosk.spring.domain.product;

import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class ProductTypeTest {

    @DisplayName("상품 타입이 재고 관련 타입인지를 체크한다")
    @Test
    void containsStockType() {
        //given
        ProductType givenType = ProductType.HANDMADE;

        //when
        boolean result = ProductType.containsStockType(givenType);

        //then
        Assertions.assertThat(result).isFalse();
    }

    @DisplayName("상품 타입이 재고 관련 타입인지를 체크한다")
    @Test
    void containsStockType2() {
        //given
        ProductType givenType = ProductType.BAKERY;

        //when
        boolean result = ProductType.containsStockType(givenType);

        //then
        Assertions.assertThat(result).isTrue();
    }

    @DisplayName("상품 타입이 재고 관련 타입인지를 체크한다")
    @CsvSource({"HANDMADE,false", "BOTTLE,true", "BAKERY,true"})
    @ParameterizedTest
    void containsStockTypeUseParameterizedTestWithCsvSource(ProductType productType, boolean expected) {
        //when
        boolean result = ProductType.containsStockType(productType);

        //then
        Assertions.assertThat(result).isEqualTo(expected);
    }


    private static Stream<Arguments> provideProductTypesForCheckingStockType() {
        return Stream.of(
            Arguments.of(ProductType.HANDMADE, false),
            Arguments.of(ProductType.BOTTLE, true),
            Arguments.of(ProductType.BAKERY, true)
        );
    }

    @DisplayName("상품 타입이 재고 관련 타입인지를 체크한다")
    @MethodSource("provideProductTypesForCheckingStockType")
    @ParameterizedTest
    void containsStockTypeUseParameterizedTestWithMethodSource(ProductType productType, boolean expected) {
        //when
        boolean result = ProductType.containsStockType(productType);

        //then
        Assertions.assertThat(result).isEqualTo(expected);
    }

}