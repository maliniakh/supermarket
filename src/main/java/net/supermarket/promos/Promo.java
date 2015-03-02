package net.supermarket.promos;

import net.supermarket.Cart;
import net.supermarket.Receipt;
import net.supermarket.items.Item;

public interface Promo {
    /**
     * this method is intended to be implemented that way it removes items from the cart and adds them to the receipt.
     * @param cart
     * @param receipt
     */
    void applyPromo(Item item, Cart cart, Receipt receipt);

    /**
     * @return name of the promotion.
     */
    String getName();
}