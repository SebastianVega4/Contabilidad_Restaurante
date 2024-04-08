package model;

public class Order {
    private ShoppingComan shoppingComan;

    public Order(ShoppingComan shoppingComan) {
        this.shoppingComan = shoppingComan;
    }

    public ShoppingComan getShoppingCart() {
        return shoppingComan;
    }

    public void setShoppingCart(ShoppingComan shoppingComan) {
        this.shoppingComan = shoppingComan;
    }
}