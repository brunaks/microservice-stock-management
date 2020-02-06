package com.bmworks.endpoints;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;

@RestController
public class StockEndpoints {

    @RequestMapping(path = "/getStock", method = RequestMethod.GET)
    public int getStock(@RequestParam String productId) {
        System.out.println("stock check called");
        return 10;
    }
}
