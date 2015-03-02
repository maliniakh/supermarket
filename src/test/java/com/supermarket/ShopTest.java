package com.supermarket;

import net.supermarket.Cart;
import net.supermarket.Receipt;
import net.supermarket.Shop;
import net.supermarket.items.Item;
import net.supermarket.promos.BuyNForMPromo;
import net.supermarket.promos.BuyNForPercentPromo;
import net.supermarket.promos.BuyNGetM4FreePromo;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ShopTest {
    private static final double DELTA = 0.00001;

    @Test
    public void testCheckout() throws Exception {
        Shop shop = new Shop();

        Cart cart = new Cart();
        cart.addItem(new Item("Milk", 1.50, new BuyNForMPromo(3, 2)), 3);
        Receipt receipt = shop.checkout(cart);
        assertEquals(3, receipt.getTotalPrice(), DELTA);
        System.out.println(receipt.toPrettyString());

        cart = new Cart();
        cart.addItem(new Item("Toblerone chocolate", 5d, new BuyNForPercentPromo(2, 0.75)), 2);
        receipt = shop.checkout(cart);
        assertEquals(7.50, receipt.getTotalPrice(), DELTA);
        System.out.println(receipt.toPrettyString());

        cart = new Cart();
        Item corkscrew = new Item("Corkscrew", null, null);
        cart.addItem(new Item("Bordeaux wine", 9.90d, new BuyNGetM4FreePromo(4, 1, corkscrew)), 4);
        receipt = shop.checkout(cart);
        assertEquals(1, (long)receipt.getItemCount(corkscrew));
        assertEquals(39.60, receipt.getTotalPrice(), DELTA);
        System.out.println(receipt.toPrettyString());

        cart = new Cart();
        cart.addItem(new Item("Ketchup", 1.65d, null), 1);
        cart.addItem(new Item("Valencia wine", 11.90d, new BuyNGetM4FreePromo(4, 1, corkscrew)), 5);
        cart.addItem(new Item("Coca Cola", 0.70, new BuyNForMPromo(3, 2)), 9);
        cart.addItem(new Item("Milka chocolate", 1.11, new BuyNForPercentPromo(2, 0.75)), 3);

        receipt = shop.checkout(cart);
        assertEquals(1, (long)receipt.getItemCount(corkscrew));
        assertEquals(67.85, receipt.getTotalPrice(), DELTA);
        System.out.println(receipt.toPrettyString());
    }
}