package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingComan {
    private final Map<Product, Integer> purchased;
    private List<Product> products;

    public ShoppingComan() {
        this.products = new ArrayList<>();
        this.purchased = new HashMap<>();
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void eraseProduct(Product product) {
        this.products.remove(product);
    }

    public double calcTotal() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice() * purchased.get(product);
        }
        return total;
    }

    public int getPurchased(Product product) {
        if (purchased.get(product) == null) return 0;
        else return purchased.get(product);
    }

    public void setPurchased(Product product, int purchasedStock) {
        purchased.put(product, purchasedStock);
    }

    public List<Product> getProducts() {
        return products;
    }

}