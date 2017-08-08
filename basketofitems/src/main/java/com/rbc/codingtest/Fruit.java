package com.rbc.codingtest;

import java.math.BigDecimal;

/**
 * A Fruit is defined by its name and price attributes
 *
 * @author Jocelyne Tchuidjeu
 */
public class Fruit implements Item{

    private final FruitName name;
    private final BigDecimal price;

    public Fruit(FruitName name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public BigDecimal getPrice() {
        return this.price;
    }
}
