package com.bmworks.usecases;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.Bean;

import static org.junit.Assert.assertEquals;

public class ProductStockManagerTests {

    @Test
    public void whenProductIsAdded_stockIsZero() {
        RegisterProduct registerProduct = new RegisterProduct(new FakeProductRepository());
        String productId = registerProduct.execute(new Product("Product"));
        ProductStockManager productStockManager = new ProductStockManager();
        assertEquals(0, productStockManager.getStockFor(productId));
    }

    @Test
    public void whenProductIsAddedWithStock10_stockIs10() {
        RegisterProduct registerProduct = new RegisterProduct(new FakeProductRepository());
        String productId = registerProduct.execute(new Product("Product"));
        ProductStockManager productStockManager = new ProductStockManager();
        productStockManager.addStockForProduct(productId, 10);
        assertEquals(10, productStockManager.getStockFor(productId));
    }

    @Test(expected = InvalidValueForStock.class)
    public void stockAddedCannotBeNegative() {
        RegisterProduct registerProduct = new RegisterProduct(new FakeProductRepository());
        String productId = registerProduct.execute(new Product("Product"));
        ProductStockManager productStockManager = new ProductStockManager();
        productStockManager.addStockForProduct(productId, -10);
    }

    @Test
    public void addAndRetrieveStockForTwoProducts() {
        RegisterProduct registerProduct = new RegisterProduct(new FakeProductRepository());
        String productId1 = registerProduct.execute(new Product("Product 1"));
        String productId2 = registerProduct.execute(new Product("Product 2"));
        ProductStockManager productStockManager = new ProductStockManager();
        productStockManager.addStockForProduct(productId1, 10);
        productStockManager.addStockForProduct(productId2, 20);
        assertEquals(10, productStockManager.getStockFor(productId1));
        assertEquals(20, productStockManager.getStockFor(productId2));
    }
}
