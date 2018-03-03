# Demo scenario

We need to develop a currency exchange service what will use an upstream service to get the currency exchange rates.  


## The mocked upstream currency exchange service

To start the upstream service go into the **demo** folder and run ```docker-compose up```.   
This will start a Wiremock Docker container on port 9000 and will return hardcoded exchange rates by using the following url pattern: 
```http://localhost:9000/{fromCcy}/{toCcy}```

At the moment the following exchange rates are supported:

| fromCcy | toCcy | exchange rate |
|--------:|------:|--------------:|
|     EUR |   HUF |        308.06 |
|     HUF |   EUR |        0.0032 |
|     HUF |   USD |        0.0039 |
|     USD |   HUF |        255.31 |
|    XBTC |   USD |      15317.20 |
|    XETH |   USD |        997.66 |

The currencies must be lowercased in url.  
Every request will have a random delay between 2-3 seconds.


## Requirements scenario

1. Our customer wants to trade currencies with 0.1% benefit. The currency exchange service should calculate the amount of 
money in the requested currency including the 0.1% benefit when calling the ```/{fromCcy}-to-{toCcy}?qty={amount}``` url.
    * only integers are supported for the amount
    * benefit must be added to the exchange rate
    * the output must use 2 decimals, rounding up the remainders
    * after the amount the full name of currency must appear  

    > i.e.: exchange 10000 HUF for EUR must result "32.04 Euro"  
    (10000 * 0.0032) * 0.1% = 32 * 0.1% = 32.032 => 32.04

    > This is the **first checkpoint**. We suppose that this is all we know from our customer. 
    Let's suppose that we released the service by marking it with a commit **release v1.0**.  

2. After a time our customer find's out that he wants to enable the trading 
of crypto-currencies. Unfortunately the upstream service at the moment supports only crypto-currency to USD exchange rates.  
Our customer wants to trade other currencies as well, using 0.15% benefit, so this is the scenario how to exchange crypto-currencies: 
    * exchange crypto-currency to USD without benefit  
    * exchange USD to requested currency with benefit  

    > i.e.: exchange 2 XETH for HUF must result "510189.29 Hungarian Forints"  
    [(2 * 997.66) * 255.31] * 0.15% = [1995.32 * 255.31] * 0.15% = 509425.1492 * 0.15% = 510189.2869238 => 510189.29  
    
    > This is the **second checkpoint**. Mark this state with a commit **release v2.0**

