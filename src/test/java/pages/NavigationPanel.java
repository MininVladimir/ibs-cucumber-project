package pages;

import io.qameta.allure.Step;
import methods.Methods;
import org.junit.jupiter.api.Tag;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Tag("@all")
public class NavigationPanel extends Methods {
    public NavigationPanel(WebDriver driver) {
        super(driver);
    }

//    Main page locators
    public static final By mainPage = By.xpath("//a[@href='/']");
    public static final By dropdownNavigation = By.xpath("//a[@data-toggle='dropdown']");
    public static final By food = By.xpath("//a[@href = '/food']");
    public static final By clearData = By.id("reset");

//    Navigation panel methods
    @Step("Return on main page")
    public MainPage mainPageClick(){
        click(mainPage);
        return new MainPage(driver);
    }

    @Step("Open dropdown menu")
    public NavigationPanel dropdownNavigationClick(){
        click(dropdownNavigation);
        return this;
    }

    @Step("Go to food page")
    public FoodPage foodClick(){
        click(food);
        return new FoodPage(driver);
    }

    @Step("Clear data")
    public NavigationPanel clearDataClick(){
        click(clearData);
        return this;
    }

}
