Feature: TradeCurrency

Scenario Outline: Trade currency with 0,1% benefit
    Given <qty> <fromCcy>
    When I exchange it to <toCcy>
    Then I should get <response>

    Examples:
     |   qty | fromCcy | toCcy |                 response |
     | 10000 |     HUF |   EUR |               32.04 Euro |
     |     1 |     USD |   HUF | 255.57 Hungarian Forints |

Scenario Outline: Trade cryptocurrency with 0,15% benefit
    Given <qty> <fromCcy>
    When I exchange it to <toCcy>
    Then I should get <response>

    Examples:
     |   qty | fromCcy | toCcy |                     response |
     |     1 |    XBTC |   HUF | 3916500.29 Hungarian Forints |
     |     2 |    XETH |   HUF |  510189.29 Hungarian Forints |
