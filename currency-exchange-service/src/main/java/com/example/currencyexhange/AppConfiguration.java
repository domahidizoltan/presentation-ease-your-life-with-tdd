package com.example.currencyexhange;

import com.example.currencyexhange.currency.CurrencyRepository;
import com.example.currencyexhange.currency.CurrencyService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfiguration {

    @Value("${config.exchangeUrl}")
    private String exchangeUrl;

    @Value("${config.exchange.benefit}")
    private double benefit;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CurrencyRepository currencyRepository(RestTemplate restTemplate) {
        return new CurrencyRepository(restTemplate, exchangeUrl);
    }

    @Bean
    public CurrencyService currencyService(CurrencyRepository currencyRepository) {
        return new CurrencyService(currencyRepository, benefit);
    }

}
