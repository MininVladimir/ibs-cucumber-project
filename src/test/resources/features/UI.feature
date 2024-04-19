@ui

Feature: UI tests

  Scenario Outline: Add product

    * Open dropdown menu
    * Go to food page
    * Click 'Добавить' button
    * Filling "<Name>" to 'Наименование' field
    * Open dropdown menu of type product
    * Choose "<Type>" product
    * Select checkbox according to the "<Checkbox value>"
    * Click 'Сохранить' button
    * Multiple assert with parameters: "<ID>", "<Name>", "<Type>", "<Checkbox value>"

    Examples:
      | ID | Name     | Type  | Checkbox value |
      | 5  | Огурец   | Овощ  | false          |
      | 5  | Огурдыня | Овощ  | true           |
      | 5  | Вишня    | Фрукт | false          |
      | 5  | Ананас   | Фрукт | true           |