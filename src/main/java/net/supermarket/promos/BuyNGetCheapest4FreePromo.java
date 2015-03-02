package net.supermarket.promos;

import net.supermarket.Cart;
import net.supermarket.Receipt;
import net.supermarket.items.Item;

import java.util.*;

/**
 * buy N items (equals or different ones) and get the cheapest one for free
 */
public class BuyNGetCheapest4FreePromo implements Promo {

    private Integer n;

    public BuyNGetCheapest4FreePromo(Integer n) {
        if(n == null || n <= 0) {
            throw new IllegalArgumentException();
        }

        this.n = n;
    }

    @Override
    public void applyPromo(Item item, Cart cart, Receipt receipt) {
        Integer count = cart.getCount(item);
        if(count == 0) {
            throw new IllegalStateException("no " + item.getName() + " item in cart");
        }

        Set<Item> promoItems = new HashSet<>();
        for (Item i : cart.getItems()) {
            if(i.getPromo().getClass() == this.getClass()) {
                promoItems.add(i);
            }
        }

        Integer freeItemsCount = promoItems.size() / n;
        Set<Item> cheapestItems = new HashSet<>();
        // items sorted by price
        List<Item> itemsSorted = new ArrayList<>(promoItems);
        Collections.sort(itemsSorted, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return (int) (100 * (o2.getPrice() - o1.getPrice()));
            }
        });

        double cheapestPrice = 0;   // to be substracted from total price
        // add freeItemsCount of items to the cheapestItems set
        double totalPrice2substract = 0;
        for(int k = itemsSorted.size() - freeItemsCount - 1; k < itemsSorted.size(); k++) {
            Item itm = itemsSorted.get(k);
            cheapestItems.add(itm);
            totalPrice2substract += itm.getPrice();
        }

        double totalPrice = count * item.getPrice();

        receipt.addItems(item, count, totalPrice, this);
        cart.removeItem(item);
    }

    @Override
    public String getName() {
        return "Buy " + n + " items, get the cheapest one free ";
    }
}