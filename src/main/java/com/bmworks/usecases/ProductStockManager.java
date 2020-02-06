package com.bmworks.usecases;

public class ProductStockManager {

    private int quantity;

    public int getStockFor(String productId) {
        return this.quantity;
    }

    public void addStockForProduct(String productId, int quantity) {
        if (quantity < 0) throw new InvalidValueForStock();
        this.quantity = quantity;
    }
}
