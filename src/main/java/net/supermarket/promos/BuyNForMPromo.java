package net.supermarket.promos;

import net.supermarket.Cart;
import net.supermarket.Receipt;
import net.supermarket.items.Item;

/**
 * buy N items and pay as for M items
 */
public class BuyNForMPromo implements Promo {

    private Integer n;
    private Integer m;

    public BuyNForMPromo(Integer n, Integer m) {
        this.n = n;
        this.m = m;
    }

    @Override
    public void applyPromo(Item item, Cart cart, Receipt receipt) {
        Integer count = cart.getCount(item);
        if(count == 0) {
            throw new IllegalStateException("no " + item.getName() + " item in cart");
        }

        // number of items left without promotion
        int reminder = count % n;
        // number of times promotion being applied
        int promoCount = (int) (count / (double) n);

        double totalPrice = promoCount * m * item.getPrice() + reminder * item.getPrice();

        receipt.addItems(item, count, totalPrice, this);
        cart.removeItem(item);
    }

    @Override
    public String getName() {
        return n + " for " + m + " promotion";
    }
}