package net.supermarket;

import net.supermarket.items.Item;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Cart {
    /**
     * stores items in cart.
     * no need to expose this collection, just a few methods are delegated.
     */
    private Map<Item, Integer> item2CountMap = new HashMap<>();

    public void addItem(Item item, Integer count) {
        Integer cnt = item2CountMap.get(item);
        if(cnt == null) {
            cnt = 0;
        }
        item2CountMap.put(item, cnt + count);
    }

    public Set<Item> getItems() {
        return item2CountMap.keySet();
    }

    public Integer getCount(Item item) {
        Integer count = item2CountMap.get(item);
        return count != null ? count : 0;
    }

    public void removeItem(Item item) {
        item2CountMap.remove(item);
    }
}