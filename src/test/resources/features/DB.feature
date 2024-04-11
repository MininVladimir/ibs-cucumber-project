@db
#@ignore

Feature: DB tests

  Scenario Outline: Deleting an added product

    * Compare all rows in table 'food' after insert row and after delete row with parameters: "<Name>", "<Type>", <Checkbox value>

    Examples:
      | Name     | Type       | Checkbox value |
      | Огурец   | VEGETABLE  | 0              |
      | Огурдыня | VEGETABLE  | 1              |
      | Вишня    | FRUIT      | 0              |
      | Ананас   | FRUIT      | 1              |