package com.example.currencyexchange.controller;

import com.example.currencyexchange.helper.CurrencyNameHelper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RestController
@RequestMapping("/")
public class CurrencyController {

    @GetMapping("{fromCcy}-to-{toCcy}")
    public String getRate(@PathVariable String fromCcy, @PathVariable String toCcy, @RequestParam Integer qty) {
        BigDecimal result = BigDecimal.valueOf(getRate(fromCcy, toCcy) * qty * 1.001).setScale(2, RoundingMode.UP);
        return String.format("%.2f %s", result, CurrencyNameHelper.getCurrencyName(toCcy));
    }

    private double getRate(String fromCcy, String toCcy) {
        RestTemplate restTemplate = new RestTemplate();
        String rate = restTemplate.getForObject("http://localhost:9000/api/v1/" + fromCcy + "/" + toCcy, String.class);
        return Double.valueOf(rate);
    }


}
