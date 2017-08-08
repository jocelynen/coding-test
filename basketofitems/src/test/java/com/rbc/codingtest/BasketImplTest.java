package com.rbc.codingtest;

import org.junit.Test;

import java.math.BigDecimal;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
  *
 * @author Jocelyne Tchuidjeu
 */
public class BasketImplTest {

   @Test
    public void calculateTheTotalCostOfABasketOfOneItem(){
       Basket fruitBasket = new BasketImpl<Fruit>();

       BigDecimal price = new BigDecimal("0.56");
       Fruit banana = new Fruit(FruitName.Banana, price);

       assertTrue(fruitBasket.add(banana));
       assertEquals(banana.getPrice(), fruitBasket.totalCost());
   }

    @Test
    public void calculateTheTotalCostOfABasketOfTwoOfTheSameItem(){
        Basket fruitBasket = new BasketImpl<Fruit>();

        BigDecimal lemonPrice = new BigDecimal("0.15");
        Fruit lemon = new Fruit(FruitName.Lemon, lemonPrice);

        assertTrue(fruitBasket.addAll(asList(lemon, lemon)));
        assertEquals(lemon.getPrice().add(lemon.getPrice()), fruitBasket.totalCost());
    }

    @Test
    public void calculateTheTotalCostOfABasketOfMixItems(){
        Basket fruitBasket = new BasketImpl<Fruit>();
        Fruit banana = new Fruit(FruitName.Banana, new BigDecimal("0.56"));
        Fruit orange = new Fruit(FruitName.Orange, new BigDecimal("1.234"));
        Fruit apple = new Fruit(FruitName.Apple, new BigDecimal("0.43"));
        Fruit lemon = new Fruit(FruitName.Lemon, new BigDecimal("0.15"));
        Fruit peach = new Fruit(FruitName.Peach, new BigDecimal("1.37"));

        assertTrue(fruitBasket.addAll(asList(banana, orange, apple, lemon, peach)));

        BigDecimal expectedPrice = calculateExpectedTotalCost(banana, orange, apple, lemon, peach);
        assertEquals(expectedPrice, fruitBasket.totalCost());
    }

    @Test
    public void calculateTheTotalCostOfAnEmptyBasket(){
        Basket fruitBasket = new BasketImpl<Fruit>();
        assertEquals(BigDecimal.ZERO, fruitBasket.totalCost());
    }

    @Test
    public void calculateTheTotalCostOfABasketContainingANegativePricedItem(){
        Basket fruitBasket = new BasketImpl<Fruit>();
        Fruit apple = new Fruit(FruitName.Apple, new BigDecimal("0.43"));
        Fruit lemon = new Fruit(FruitName.Lemon, new BigDecimal("-2.21"));
        Fruit peach = new Fruit(FruitName.Peach, new BigDecimal("1.37"));

        assertTrue(fruitBasket.addAll(asList(apple, lemon, peach)));
        assertEquals(apple.getPrice().add(peach.getPrice()), fruitBasket.totalCost());
    }

    @Test
    public void calculateTheTotalCostOfABasketContainingAZeroPricedItem(){
        Basket fruitBasket = new BasketImpl<Fruit>();
        Fruit apple = new Fruit(FruitName.Apple, new BigDecimal("0"));
        Fruit lemon = new Fruit(FruitName.Lemon, new BigDecimal("2.21"));
        Fruit peach = new Fruit(FruitName.Peach, new BigDecimal("1.37"));

        assertTrue(fruitBasket.addAll(asList(apple, lemon, peach)));
        assertEquals(lemon.getPrice().add(peach.getPrice()), fruitBasket.totalCost());
    }

    @Test
    public void addingANullItemToTheBasketDoesNothing(){
        Basket fruitBasket = new BasketImpl<Fruit>();

        assertFalse(fruitBasket.add(null));
        assertEquals(BigDecimal.ZERO, fruitBasket.totalCost());
    }

    @Test
    public void addingANullItemListToTheBasketDoesNothing(){
        Basket fruitBasket = new BasketImpl<Fruit>();

        assertFalse(fruitBasket.addAll(null));
        assertEquals(BigDecimal.ZERO, fruitBasket.totalCost());
    }

    private BigDecimal calculateExpectedTotalCost(Fruit... fruits) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Fruit fruit : fruits) {
            totalPrice = totalPrice.add(fruit.getPrice());
        }
        return totalPrice;
    }
 }
