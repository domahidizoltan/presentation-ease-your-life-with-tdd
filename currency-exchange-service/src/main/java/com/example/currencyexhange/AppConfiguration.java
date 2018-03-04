package com.example.currencyexhange;

import com.example.currencyexhange.currency.CurrencyRepository;
import com.example.currencyexhange.currency.CurrencyService;
import com.example.currencyexhange.currency.calculator.ExchangePriceCalculatorFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties(ConfigProperties.class)
public class AppConfiguration {


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CurrencyRepository currencyRepository(RestTemplate restTemplate, ConfigProperties configProperties) {
        return new CurrencyRepository(restTemplate, configProperties.getUrl());
    }

    @Bean
    public CurrencyService currencyService(CurrencyRepository currencyRepository, ExchangePriceCalculatorFactory exchangePriceCalculatorFactory) {
        return new CurrencyService(currencyRepository, exchangePriceCalculatorFactory);
    }

}
