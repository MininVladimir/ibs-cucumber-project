package properties;

public class BrowserProperties {

    public static final String browserName = System.getProperty("browser");
    public static final String URL = "http://localhost:8080/";
    public static final int pageLoadTimeout = 10;
    public static final int implicitlyWait = 10;
}
