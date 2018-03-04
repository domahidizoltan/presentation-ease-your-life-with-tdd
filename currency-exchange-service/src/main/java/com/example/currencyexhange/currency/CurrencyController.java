package com.example.currencyexhange.currency;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class CurrencyController {

    @GetMapping("{fromCcy}-to-{toCcy}")
    public String exchangeCurrencies(@PathVariable String fromCcy, @PathVariable String toCcy, @RequestParam Integer qty) {
        return null;
    }

}
