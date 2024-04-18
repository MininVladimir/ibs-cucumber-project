package tests.ui_tests;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.qameta.allure.Step;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
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

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;
import java.util.Map;

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
            if(browserName.toLowerCase().startsWith("local")){
                driver.get(URLMap.get("local_URL"));
            } else if(browserName.toLowerCase().startsWith("remote")){
                driver.get(URLMap.get("remote_URL"));
            } else System.out.println("Invalid browser name");
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

    @Step("{browser} init")
    public void setupBrowser(String browser) throws MalformedURLException {
        driver = null;
        switch (browser.toLowerCase()) {
            case "local_firefox":
                WebDriverManager.firefoxdriver().clearDriverCache().setup();
                WebDriverManager.firefoxdriver().clearResolutionCache().setup();
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless");
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "local_chrome":
                WebDriverManager.chromedriver().clearDriverCache().setup();
                WebDriverManager.chromedriver().clearResolutionCache().setup();
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless=new");
                driver = new ChromeDriver(chromeOptions);
                break;
            case "local_edge":
                WebDriverManager.edgedriver().clearDriverCache().setup();
                WebDriverManager.edgedriver().clearResolutionCache().setup();
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--headless=new");
                driver = new EdgeDriver(edgeOptions);
                break;
            case "remote_firefox":
                initRemoteDriver(remoteDriverMap.get("firefox"), remoteDriverMap.get("version 109"));
                break;
            case "remote_chrome":
                initRemoteDriver(remoteDriverMap.get("chrome"), remoteDriverMap.get("version 109"));
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

    public void initRemoteDriver(String browserName, String browserVersion) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("browserVersion", browserVersion);
        capabilities.setCapability("selenoid:options", Map.<String, Object>of("enableVNC", true));
        driver = new RemoteWebDriver(URI.create(URLMap.get("remote_driver_URL")).toURL(), capabilities);
    }
}
