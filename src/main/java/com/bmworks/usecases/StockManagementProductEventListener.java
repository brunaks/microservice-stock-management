package com.bmworks.usecases;

class StockManagementProductEventListener implements ProductEventListener {

    private StockRepository stockRepository;

    public StockManagementProductEventListener(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public void onProductCreated(Product product) {
        stockRepository.save(product.getName(), 0);
    }
}
