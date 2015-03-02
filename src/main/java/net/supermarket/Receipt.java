package net.supermarket;

import net.supermarket.items.Item;
import net.supermarket.promos.Promo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Receipt {
    private List<ReceiptRecord> records = new ArrayList<ReceiptRecord>();

    private Double totalPrice = 0d;

    /**
     * @param item
     * @param count
     * @param price
     * @param promo might be null, if so, promotion was not applied
     */
    public void addItems(Item item, Integer count, Double price, Promo promo) {
        ReceiptRecord rr = new ReceiptRecord(item, count, price, promo);

        records.add(rr);
        totalPrice += price;
    }

    public Integer getItemCount(Item item) {
        // should be optimised
        for (ReceiptRecord rr : records) {
            if(rr.item.equals(item)) {
                return rr.count;
            }
        }

        return 0;
    }

    public String toPrettyString() {
        StringBuffer result = new StringBuffer("RECEIPT\n");

        for (ReceiptRecord rr : records) {
            result.append(rr.toPrettyString()).append("\n");
        }

        return result.append("Total price: ").append(String.format("%.2f", totalPrice)).append("\n").toString();
    }

    public Double getTotalPrice() {
        return totalPrice;
    }
}

class ReceiptRecord {
    Item item;
    Integer count;
    Double totalPrice;
    Promo promo;

    ReceiptRecord(Item item, Integer count, Double totalPrice, Promo promo) {
        this.item = item;
        this.count = count;
        this.totalPrice = totalPrice;
        this.promo = promo;
    }

    public String toPrettyString() {
        return String.format("%20s x %d  %-30s %.2f", item.getName(), count, promo != null ? "(" + promo.getName() + ")" : "", totalPrice);
    }
}