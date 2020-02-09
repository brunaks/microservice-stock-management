package com.bmworks.usecases;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class InMemoryStockRepository implements StockRepository {

    private Map<String, Integer> stockPerProductId = new HashMap<>();

    @Override
    public void save(String productId, int quantity) {
        stockPerProductId.put(productId, quantity);
    }

    @Override
    public Optional<Integer> find(String productId) {
        return Optional.ofNullable(stockPerProductId.get(productId));
    }
}
