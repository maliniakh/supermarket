package net.supermarket.promos;

import net.supermarket.Cart;
import net.supermarket.Receipt;
import net.supermarket.items.Item;

public class BuyNForPercentPromo implements Promo {

    private Integer n;

    private Double percent;

    public BuyNForPercentPromo(Integer n, Double percent) {
        if(n == null || n <= 0) {
            throw new IllegalArgumentException();
        }
        if(percent == null || percent <= 0 || percent >= 1) {
            throw new IllegalArgumentException();
        }

        this.n = n;
        this.percent = percent;
    }

    @Override
    public void applyPromo(Item item, Cart cart, Receipt receipt) {
        Integer count = cart.getCount(item);
        if(count == 0) {
            throw new IllegalStateException("no " + item.getName() + " item in cart");
        }

        double totalPrice = count * item.getPrice();

        // apply promo only if at least n items are in the cart
        if(count >= n) {
            totalPrice *= percent;
        }

        receipt.addItems(item, count, totalPrice, this);
        cart.removeItem(item);
    }

    @Override
    public String getName() {
        return "Buy " + n + " items for " + String.format("%.2f", percent) + "% of price promotion";
    }
}