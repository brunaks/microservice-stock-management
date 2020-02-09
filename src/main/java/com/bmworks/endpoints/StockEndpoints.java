package com.bmworks.endpoints;

import com.bmworks.usecases.ProductStockManager;
import com.bmworks.usecases.StockRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;

@RestController
public class StockEndpoints {

    private StockRepository stockRepository;

    public StockEndpoints(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @RequestMapping(path = "/getStock", method = RequestMethod.GET)
    public int getStock(@RequestParam String productId) {
        return new ProductStockManager(stockRepository).getStockFor(productId);
    }
}
