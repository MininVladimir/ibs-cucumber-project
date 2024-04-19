package pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FoodPage extends MainPage {
    public FoodPage(WebDriver driver) {
        super(driver);
    }

//    Food locators
    public static final By addButton = By.xpath("//button[@data-toggle='modal']");
    public static final By name = By.id("name");
    public static final By dropdownType = By.id("type");
    public static final By vegetable = By.xpath("//option[@value = 'VEGETABLE']");
    public static final By fruit = By.xpath("//option[@value = 'FRUIT']");
    public static final By exoticCheckbox = By.id("exotic");
    public static final By saveButton = By.id("save");

//    Assert locators
    public static final By productID = By.xpath("//th[text()='5']");
    public static final By productName = By.xpath("//th[text()='5']/following-sibling::td[1]");
    public static final By productType = By.xpath("//th[text()='5']/following-sibling::td[2]");
    public static final By exoticStatus = By.xpath("//th[text()='5']/following-sibling::td[3]");

//    Food methods
    @Step("Click 'Добавить' button")
    public FoodPage addButtonClick(){
        click(addButton);
        return this;
    }

    @Step("Filling value to 'Наименование' field")
    public FoodPage nameFieldFilling(String value){
        sendKeys(name, value);
        return this;
    }

    @Step("Open dropdown menu of type product")
    public FoodPage dropdownTypeClick(){
        click(dropdownType);
        return this;
    }

    @Step("Choose type product")
    public FoodPage typeProductChoose(String type) {
        switch(type){
            case "Овощ":
                click(vegetable);
                break;
            case "Фрукт":
                click(fruit);
                break;
        }
        return this;
    }

    @Step("Select checkbox according to the value")
    public FoodPage selectExoticCheckbox(String value) {
        if (value.equals("true")) {
            click(exoticCheckbox);
        }
        return this;
    }

    @Step("Click 'Сохранить' button")
    public FoodPage saveButtonClick(){
        click(saveButton);
        return new FoodPage(driver);
    }

    @Step("Clear data")
    public FoodPage clearDataClick(){
        click(clearData);
        return this;
    }

    @Step("Multiple assert with parameters")
    public FoodPage multipleAssert(String value1,
                                   String value2,
                                   String value3,
                                   String value4){
        Assertions.assertAll(
            () -> assertEqualsGetText(productID, value1),
            () -> assertEqualsGetText(productName, value2),
            () -> assertEqualsGetText(productType, value3),
            () -> assertEqualsGetText(exoticStatus, value4)
        );
        return this;
    }


}
