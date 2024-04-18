package properties;

import java.util.HashMap;
import java.util.Map;

public class BrowserProperties {

    public static final Map<String, String> URLMap;
    public static final Map<String, String> remoteDriverMap;

    static {
        URLMap = new HashMap<>();
        URLMap.put("local_URL", "http://localhost:8080/");
        URLMap.put("remote_URL", "http://149.154.71.152:8080/");
        URLMap.put("remote_driver_URL", "http://149.154.71.152:4444/wd/hub");
    }

    static {
        remoteDriverMap = new HashMap<>();
        remoteDriverMap.put("firefox", "firefox");
        remoteDriverMap.put("chrome", "chrome");
        remoteDriverMap.put("version 108", "108.0");
        remoteDriverMap.put("version 109", "109.0");
    }

    public static final String browserName = System.getProperty("browser");
    public static final int pageLoadTimeout = 10;
    public static final int implicitlyWait = 10;
}
