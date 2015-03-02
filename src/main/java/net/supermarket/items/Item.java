package net.supermarket.items;

import net.supermarket.promos.Promo;

/**
 */
public class Item {
    private String name;
    private final Double price;
    private Promo promo;

    public Item(String name, Double price, Promo promo) {
        this.name = name;
        this.price = price;
        this.promo = promo;
    }

    public Double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public Promo getPromo() {
        return promo;
    }

    /**
     * notice that promo is not taken into account in equals / hashCode methods
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;

        Item item = (Item) o;

        if (name != null ? !name.equals(item.name) : item.name != null) return false;
        if (price != null ? !price.equals(item.price) : item.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}