@api

Feature: API tests

  Scenario: Get product list

    * Get product list

  Scenario Outline: Add product

    * Add product with parameters: "<Name>", "<Type>", "<Exotic>"

    Examples:
      | Name     | Type       | Exotic |
      | Огурец   | VEGETABLE  | false  |
      | Огурдыня | VEGETABLE  | true   |
      | Вишня    | FRUIT      | false  |
      | Ананас   | FRUIT      | true   |

  Scenario: Clear data

    * Clear data