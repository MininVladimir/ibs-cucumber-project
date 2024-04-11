package methods;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static properties.BrowserProperties.implicitlyWait;

public class Methods{
    protected WebDriver driver;

    public Methods(WebDriver driver){
        this.driver = driver;
    }

//    Actions
    @Step("Find element")
    public WebElement findElement(By locator){
        WebElement element = driver.findElement(locator);
        return element;
    }

    @Step("Wait until wait until the element becomes visible")
    public WebElement waitElementToBeVisible(By locator){
        new WebDriverWait(driver, Duration.ofSeconds(implicitlyWait))
                                  .until(ExpectedConditions
                                  .visibilityOf(findElement(locator))
        );
        return findElement(locator);
    }

    @Step("Wait until wait until the element becomes clickable")
    public WebElement waitElementToBeClickable(By locator){
        new WebDriverWait(driver, Duration.ofSeconds(implicitlyWait))
                                  .until(ExpectedConditions
                                  .elementToBeClickable(findElement(locator))
        );
        return findElement(locator);
    }

    @Step("Scroll to element")
    public void scrollToElement(By locator) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(true);", findElement(locator));
    }

    @Step("Click element")
    public void click(By locator){
        scrollToElement(locator);
        waitElementToBeVisible(locator);
        waitElementToBeClickable(locator).click();
    }

    @Step("Clear text field")
    public void clearField(By locator){
        scrollToElement(locator);
        waitElementToBeVisible(locator);
        waitElementToBeClickable(locator);
        findElement(locator).clear();
    }

    @Step("Filling text field")
    public void sendKeys(By locator,
                         String value){
        clearField(locator);
        findElement(locator).sendKeys(value);
    }

    public void sendKeysForSpecialField(By locator,
                                        String value){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute(arguments[1],arguments[2])",
                         findElement(locator), "value", value);
    }

    public String getCurrentURL(){
        String CurrentUrl = driver.getCurrentUrl();
        return CurrentUrl;
    }

    public void moveToElement(By locator){
        waitElementToBeVisible(locator);
        new Actions(driver).moveToElement(findElement(locator)).perform();
    }

//    Asserts

    public void assertTrueElementIsDisplayed(By locator){
        scrollToElement(locator);
        waitElementToBeVisible(locator);
        Assertions.assertTrue(findElement(locator).isDisplayed());
    }

    public void assertTrueElementContainsText(By locator,
                                              String value){
        scrollToElement(locator);
        waitElementToBeVisible(locator);
        Assertions.assertTrue(findElement(locator).isDisplayed());
        Assertions.assertTrue(findElement(locator).getText()
                                              .contains(value)
        );
    }

    public void assertTrueElementContainsAttributeValue(By locator,
                                                        String attribute,
                                                        String attributeValue){
        scrollToElement(locator);
        waitElementToBeVisible(locator);
        Assertions.assertTrue(findElement(locator).isDisplayed());
        Assertions.assertTrue(findElement(locator).getAttribute(attribute)
                                              .contains(attributeValue)
        );
    }

    public void assertNavigated(String value){
        Assertions.assertEquals(getCurrentURL(), value);
    }

    @Step("Assert")
    public void assertEqualsGetText(By locator,
                                    String value){
        scrollToElement(locator);
        Assertions.assertEquals(findElement(locator).getText(), value);
    }
}
