package com.bmworks.usecases;

import java.util.Optional;

public interface StockRepository {
    void save(String productId, int quantity);
    Optional<Integer> find(String productId);
}
