package com.rbc.codingtest;

import java.math.BigDecimal;
import java.util.List;

/**
 * Represents a collection of items
 *
 * @author Jocelyne Tchuidjeu
 */
public interface Basket<T extends Item>{
    /**
     * Add a new item to the basket
     *
     * @param newItem item to be added
     * @return <tt>true</tt> if the item's added successfully
     */
    boolean add(Item newItem);

    /**
     * Add a collection of new items to the basket
     *
     * @param newItems items to be added
     * @return <tt>true</tt> if the items were added successfully
     */
    boolean addAll(List<Item> newItems);

    /**
     * calculate the total cost of the basket's items.
     * Invalid items with negative prices are filtered out.
     * No upper limit price has been defined.
     *
     * @return sum of all item's price
     */
    BigDecimal totalCost();
}
