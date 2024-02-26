package com.sample.cafekiosk.unit.order;

import com.sample.cafekiosk.unit.beverage.Beverage;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Order {

    private final LocalDateTime orderDatetime;
    private final List<Beverage> beverages;

}
