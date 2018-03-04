package com.example.currencyexchange;

import com.example.currencyexchange.helper.CurrencyNameHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CurrencyITest {

    @Autowired
    private MockMvc mockMvc;

    private double rate = 0;

    @Before
    public void setUp() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String rateResponse = restTemplate.getForObject("http://localhost:9000/api/v1/eur/huf", String.class);
        rate = Double.valueOf(rateResponse);
    }

    @Test
    public void shouldConvert20EurToHuf() throws Exception {
        MockHttpServletRequestBuilder get20 = MockMvcRequestBuilders.get( "http://localhost:8080/eur-to-huf?qty=20");
        BigDecimal val = BigDecimal.valueOf(rate * 20 * 1.001).setScale(2, RoundingMode.UP);
        String result = String.format("%.2f %s", val, CurrencyNameHelper.getCurrencyName("huf"));
        mockMvc.perform(get20).andExpect(MockMvcResultMatchers.content().string(result));
    }

}
