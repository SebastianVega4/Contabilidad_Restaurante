package model;

public class Order {
    private ShoppingComan shoppingComan;

    public Order(ShoppingComan shoppingComa) {
        this.shoppingComan = shoppingComa;
    }

    public ShoppingComan getShoppingCart() {
        return shoppingComan;
    }
}