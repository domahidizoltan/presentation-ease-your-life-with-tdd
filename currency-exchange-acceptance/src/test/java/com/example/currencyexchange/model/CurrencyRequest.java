package com.example.currencyexchange.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class CurrencyRequest {

    int qty;
    String fromCcy;
    String toCcy;

    public Map<String, String> toMap() {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("qty", String.valueOf(qty));
        requestMap.put("fromCcy", fromCcy.toLowerCase());
        requestMap.put("toCcy", toCcy.toLowerCase());

        return requestMap;
    }
}
