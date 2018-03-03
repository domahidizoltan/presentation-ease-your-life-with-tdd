package com.example.currencyexchange.steps;

import com.example.currencyexchange.model.CurrencyRequest;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static org.junit.Assert.assertEquals;

@Slf4j
public class TradeCurrencyStepsDef {

    private String hostUrl = "http://localhost:8080/";
    private String exchangeUrlPath = "{fromCcy}-to-{toCcy}?qty={qty}";

    private RestTemplate restTemplate = new RestTemplate();

    CurrencyRequest request;

    @Given("^(\\d+) (X?[A-Z]{3})$")
    public void givenXQtyOfCurrency(int qty, String fromCcy) {
        log.debug("Given {} {}", qty, fromCcy);
        request = new CurrencyRequest();
        request.setQty(qty);
        request.setFromCcy(fromCcy);
    }

    @When("^I exchange it to ([A-Z]{3})$")
    public void iExchangeItToCurrency(String toCcy) {
        log.debug("When I exchange it to {}", toCcy);
        request.setToCcy(toCcy);
    }

    @Then("^I should get (.*)$")
    public void iShouldGetResponse(String expectedResponse) {
        log.debug("Then I should get {}", expectedResponse);
        String url = getUrl();
        String actualResponse = restTemplate.getForObject(url, String.class);
        log.debug("Got following response from server when calling {}: {}", url, actualResponse);
        assertEquals(expectedResponse, actualResponse);
    }

    private String getUrl() {
        String urlWithPlaceholder = hostUrl + exchangeUrlPath;
        String uri = UriComponentsBuilder
            .fromUriString(urlWithPlaceholder)
            .buildAndExpand(request.toMap())
            .toUriString();

        return uri;
    }

}
