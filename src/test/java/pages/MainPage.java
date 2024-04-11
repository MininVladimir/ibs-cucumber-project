package pages;

import org.junit.jupiter.api.Tag;
import org.openqa.selenium.WebDriver;

@Tag("@all")
public class MainPage extends NavigationPanel {
    public MainPage(WebDriver driver) {
        super(driver);
    }
}
