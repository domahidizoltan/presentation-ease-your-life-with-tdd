package com.example.currencyexhange.currency;

import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/")
public class CurrencyController {

    private CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }
    
    @GetMapping("{fromCcy}-to-{toCcy}")
    public String exchangeCurrencies(@PathVariable String fromCcy, @PathVariable String toCcy, @RequestParam Integer qty) {
        BigDecimal exchangePrice = currencyService.exchangeCurrencies(fromCcy, toCcy, qty);
        String currencyName = currencyService.getNameOfCurrency(toCcy);
        return String.format("%.2f %s", exchangePrice, currencyName);
    }

}
