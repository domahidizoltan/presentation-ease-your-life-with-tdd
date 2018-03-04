package com.example.currencyexhange;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("config.exchange")
public class ConfigProperties {

    private String url;
    private double simpleBenefit;
    private double cryptoBenefit;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getSimpleBenefit() {
        return simpleBenefit;
    }

    public void setSimpleBenefit(double simpleBenefit) {
        this.simpleBenefit = simpleBenefit;
    }

    public double getCryptoBenefit() {
        return cryptoBenefit;
    }

    public void setCryptoBenefit(double cryptoBenefit) {
        this.cryptoBenefit = cryptoBenefit;
    }
}
