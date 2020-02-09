package com.bmworks.usecases;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ProductStockManagerTests {

    private InMemoryStockRepository fakeProductRepository;
    private RegisterProduct registerProduct;

    private ProductStockManager makeStockManager() {
        return new ProductStockManager(fakeProductRepository);
    }

    @Before
    public void setUp() {
        fakeProductRepository = new InMemoryStockRepository();
        registerProduct = new RegisterProduct(new StockManagementProductEventListener(fakeProductRepository));
    }

    @Test
    public void whenProductIsAdded_stockIsZero() {
        String productId = registerProduct.execute(new Product("Product"));
        ProductStockManager productStockManager = makeStockManager();
        assertEquals(0, productStockManager.getStockFor(productId));
    }

    @Test
    public void whenProductIsAddedWithStock10_stockIs10() {
        String productId = registerProduct.execute(new Product("Product"));
        ProductStockManager productStockManager = makeStockManager();
        productStockManager.addStockForProduct(productId, 10);
        assertEquals(10, productStockManager.getStockFor(productId));
    }

    @Test
    public void stockAddedCannotBeNegative() {
        String productId = registerProduct.execute(new Product("Product"));
        ProductStockManager productStockManager = makeStockManager();
        try {
            productStockManager.addStockForProduct(productId, -10);
            fail();
        } catch (InvalidValueForStock e) {
            assertEquals(0, productStockManager.getStockFor(productId));
        }
    }

    @Test
    public void addAndRetrieveStockForTwoProducts() {
        String productId1 = registerProduct.execute(new Product("Product 1"));
        String productId2 = registerProduct.execute(new Product("Product 2"));
        ProductStockManager productStockManager = makeStockManager();
        productStockManager.addStockForProduct(productId1, 10);
        productStockManager.addStockForProduct(productId2, 20);
        assertEquals(10, productStockManager.getStockFor(productId1));
        assertEquals(20, productStockManager.getStockFor(productId2));
    }

    @Test
    public void stockMustBePersistedBetweenStockManagerInstances() {
        String productId = registerProduct.execute(new Product("Product"));
        ProductStockManager productStockManager = makeStockManager();
        productStockManager.addStockForProduct(productId, 10);

        productStockManager = makeStockManager();
        assertEquals(10, productStockManager.getStockFor(productId));
    }

    @Test
    public void stockMustBeSummedUpForProduct() {
        String productId = registerProduct.execute(new Product("Product"));
        ProductStockManager productStockManager = makeStockManager();
        productStockManager.addStockForProduct(productId, 10);
        productStockManager.addStockForProduct(productId, 30);
        assertEquals(40, productStockManager.getStockFor(productId));
    }

    @Test
    public void whenProductWithStock10_StockRemovedIs5_StockIsNow5() {
        String productId = registerProduct.execute(new Product("Product"));
        ProductStockManager productStockManager = makeStockManager();
        productStockManager.addStockForProduct(productId, 10);
        productStockManager.removeStockForProduct(productId, 5);
        assertEquals(5, productStockManager.getStockFor(productId));
    }

    @Test
    public void whenProductWithStock10_StockRemovedIs15_throwsException() {
        String productId = registerProduct.execute(new Product("Product"));
        ProductStockManager productStockManager = makeStockManager();
        productStockManager.addStockForProduct(productId, 10);
        try {
            productStockManager.removeStockForProduct(productId, 15);
            fail();
        }
        catch (InvalidValueForStock e) {
            assertEquals(10, productStockManager.getStockFor(productId));
        }
    }

    @Test(expected = UnknownProductIdException.class)
    public void stockForUnknownProduct_throwsException() {
        ProductStockManager productStockManager = makeStockManager();
        productStockManager.getStockFor("WRONG Product");
    }
}
