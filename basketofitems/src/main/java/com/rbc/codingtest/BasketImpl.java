package com.rbc.codingtest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple implementation of Basket of items - (Ignores any currency/formatting)
 *
 * @author Jocelyne Tchuidjeu
 */

public class BasketImpl<T extends Item> implements Basket<T> {
    private final List<Item> items = new ArrayList<>();

    @Override
    public boolean add(Item newItem) {
        if (null == newItem) return false;
        return this.items.add(newItem);
    }


    @Override
    public boolean addAll(List<Item> newItems) {
        if (null == newItems) return false;
        boolean added = false;
        for (Item newItem : newItems) {
           added = add(newItem) || added;
        }
        return added;
    }

    @Override
    public BigDecimal totalCost() {
        return this.items.stream().filter(item -> item.getPrice().compareTo(BigDecimal.ZERO) > 0)
                .map(Item::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
