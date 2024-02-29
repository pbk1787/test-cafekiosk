package com.sample.cafekiosk.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.sample.cafekiosk.unit.beverage.Americano;
import com.sample.cafekiosk.unit.beverage.Latte;
import org.junit.jupiter.api.Test;

class CafeKioskTest {

    //이런 식으로 테스트 코드를 작성하였을 때의 문제점 (수동 테스트)
    // 1. 결국 사람이 console 로그를 통해 데이터를 확인해야함
    // 2. 다른 사람이 이 코드를 봤을때 어떤 것을 검증해야하는지 알 수 없음
    @Test
    void add_menual_test() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Americano());

        System.out.println(">> 음료 수 : " + cafeKiosk.getBeverages().size());
        System.out.println(">> 음료 명 : " + cafeKiosk.getBeverages().get(0).getName());
    }

    @Test
    void add() {
        CafeKiosk cafeKiosk = new CafeKiosk();

        cafeKiosk.add(new Americano());

        assertThat(cafeKiosk.getBeverages()).hasSize(1);
        assertThat(cafeKiosk.getBeverages().get(0).getName()).isEqualTo("아메리카노");
    }

    @Test
    void addSeveralBeverages_happy_case1() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        cafeKiosk.add(americano, 1);

        assertThat(cafeKiosk.getBeverages()).hasSize(1);
        assertThat(cafeKiosk.getBeverages().get(0)).isEqualTo(americano);
    }

    @Test
    void addSeveralBeverages_happy_case2() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        cafeKiosk.add(americano, 2);

        assertThat(cafeKiosk.getBeverages()).hasSize(2);
        assertThat(cafeKiosk.getBeverages().get(0)).isEqualTo(americano);
        assertThat(cafeKiosk.getBeverages().get(1)).isEqualTo(americano);
    }

    @Test
    void addSeveralBeverages_error_case() {
        CafeKiosk cafeKiosk = new CafeKiosk();

        assertThatThrownBy(() -> cafeKiosk.add(new Americano(), 0))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("음료는 1잔 이상 주문하실 수 있습니다.");
    }

    @Test
    void remove() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        cafeKiosk.add(americano);
        assertThat(cafeKiosk.getBeverages()).hasSize(1);

        cafeKiosk.remove(americano);

        assertThat(cafeKiosk.getBeverages()).isEmpty();
    }

    @Test
    void clear() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Americano());
        cafeKiosk.add(new Latte());
        assertThat(cafeKiosk.getBeverages()).hasSize(2);

        cafeKiosk.clear();

        assertThat(cafeKiosk.getBeverages()).isEmpty();
    }

}