package net.supermarket;

import net.supermarket.items.Item;
import net.supermarket.promos.Promo;

public class Shop {
    public Receipt checkout(Cart cart) {
        Receipt receipt = new Receipt();

        while(cart.getItems().size() > 0) {
            Item item = cart.getItems().iterator().next();
            Integer count = cart.getCount(item);
            Double totalPrice = item.getPrice() * count;

            // no promo -> just calculate the price, add items to the receipt and remove them from the cart
            Promo promo = item.getPromo();
            if(promo == null) {
                receipt.addItems(item, count, totalPrice, null);
                cart.removeItem(item);
                continue;
            }

            promo.applyPromo(item, cart, receipt);
        }

        return receipt;
    }
}
