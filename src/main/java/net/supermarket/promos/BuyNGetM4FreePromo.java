package net.supermarket.promos;

import net.supermarket.Cart;
import net.supermarket.Receipt;
import net.supermarket.items.Item;

/**
 * buy N items and get M items for free
 */
public class BuyNGetM4FreePromo implements Promo {

    private Integer n;
    private Integer m;
    private Item freeItem;

    public BuyNGetM4FreePromo(Integer n, Integer m, Item freeItem) {
        if(n == null || n <= 0) {
            throw new IllegalArgumentException();
        }
        if(m == null ||m <= 0) {
            throw new IllegalArgumentException();
        }

        this.n = n;
        this.m = m;
        this.freeItem = freeItem;
    }

    @Override
    public void applyPromo(Item item, Cart cart, Receipt receipt) {
        Integer count = cart.getCount(item);
        if(count == 0) {
            throw new IllegalStateException("no " + item.getName() + " item in cart");
        }

        double totalPrice = count * item.getPrice();

        receipt.addItems(item, count, totalPrice, this);
        cart.removeItem(item);

        // number of free items to be added to the receipt
        int freeItemsCount = m * count / n;
        if(freeItemsCount > 0) {
            receipt.addItems(freeItem, freeItemsCount, 0d, this);
        }
    }

    @Override
    public String getName() {
        return "Buy " + n + " items, get " + m + " free " + (m == 1 ? "item":"items");
    }
}