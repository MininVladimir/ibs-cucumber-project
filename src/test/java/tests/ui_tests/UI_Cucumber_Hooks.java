package tests.ui_tests;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.qameta.allure.Step;
import pages.MainPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import static properties.BrowserProperties.*;

public class UI_Cucumber_Hooks {

    private final Logger logger = LoggerFactory.getLogger(UI_Cucumber_Hooks.class);
    public static WebDriver driver;
    public static MainPage mainPage;

    @Before("@ui")
    @Step("Open browser and start application")
    public void openBrowserAndStartApplication() {
        try {
            setupBrowser(browserName);
            driver.get(URL);
            mainPage = new MainPage(driver);
        } catch (Exception e) {
            logger.error("\u001B[31m" + "@Before error: " + e.getMessage() + "\u001B[0m");
        }
    }

    @After("@ui")
    @Step("Tear down")
    public void tearDown(){
        try {
            mainPage.dropdownNavigationClick();
            mainPage.clearDataClick();
            killDriver();
        }catch (Exception e) {
            logger.error("\u001B[31m" + "@After error: " + e.getMessage() + "\u001B[0m");
        }
    }

    public void setupBrowser(String browser) {
        driver = null;
        switch (browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                driver = new ChromeDriver(chromeOptions);
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                driver = new EdgeDriver(edgeOptions);
                break;
            default:
                System.out.println("Invalid browser name");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTimeout));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitlyWait));
    }

    @Step("Delete cookies and quit driver")
    public void killDriver(){
        driver.manage().deleteAllCookies();
        driver.quit();
        driver = null;
    }
}
