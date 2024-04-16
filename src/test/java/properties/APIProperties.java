package properties;

import java.util.HashMap;
import java.util.Map;

public class APIProperties {

    public static final String URI = "http://localhost:8080";
    public static final Map<String, String> endpointMap;
    public static final Map<String, Integer> statusCodeMap;
    public static final Map<String, String> jsonPathMap;

    static {
        endpointMap = new HashMap<>();
        endpointMap.put("food", "/api/food");
        endpointMap.put("reset", "/api/data/reset");
    }

    static {
        statusCodeMap = new HashMap<>();
        statusCodeMap.put("OK", 200);
    }

    static {
        jsonPathMap = new HashMap<>();
        jsonPathMap.put("all", "$..");
    }

}
