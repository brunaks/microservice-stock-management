package com.bmworks.usecases;

public class ProductStockManager {

    private StockRepository stockRepository;

    public ProductStockManager(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public int getStockFor(String productId) {
        return stockRepository.find(productId).orElseThrow(UnknownProductIdException::new);
    }

    public void addStockForProduct(String productId, int quantity) {
        if (quantity < 0) throw new InvalidValueForStock();
        stockRepository.save(productId, getStockFor(productId) + quantity);
    }

    public void removeStockForProduct(String productId, int quantity) {
        if (getStockFor(productId) - quantity < 0)
            throw new InvalidValueForStock();
        else
            stockRepository.save(productId, getStockFor(productId) - quantity);
    }
}
