package com.example.currencyexhange.currency.calculator;

import com.example.currencyexhange.ConfigProperties;
import com.example.currencyexhange.currency.CurrencyRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CalculatorConfiguration {

    @Bean
    public ExchangePriceCalculatorFactory exchangePriceCalculatorFactory(CurrencyRepository currencyRepository, ConfigProperties configProperties) {
        Map<String, ExchangePriceCalculator> calculatorMap = new HashMap<>();
        calculatorMap.put("simple", simpleCalculator(currencyRepository, configProperties));
        calculatorMap.put("crypto", cryptoCalculator(currencyRepository, configProperties));

        return new ExchangePriceCalculatorFactory(calculatorMap);
    }

    private CryptoExchangePriceCalculator cryptoCalculator(CurrencyRepository currencyRepository, ConfigProperties configProperties) {
        return new CryptoExchangePriceCalculator(currencyRepository, configProperties.getCryptoBenefit(), configProperties.getBaseCurrency());
    }

    private SimpleExchangePriceCalculator simpleCalculator(CurrencyRepository currencyRepository, ConfigProperties configProperties) {
        return new SimpleExchangePriceCalculator(currencyRepository, configProperties.getSimpleBenefit());
    }


}
