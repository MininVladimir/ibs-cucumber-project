package tests;

import org.junit.platform.suite.api.*;

import static io.cucumber.core.options.Constants.*;
import static properties.CucumberRunnerProperties.*;

@Suite
@IncludeEngines("cucumber")
@ConfigurationParameters({@ConfigurationParameter(key = FEATURES_PROPERTY_NAME, value = FEATURES_PROPERTY_NAME_VALUE),
                          @ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = GLUE_PROPERTY_NAME_VALUE),
                          @ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = PLUGIN_PROPERTY_NAME_VALUE)
})
public class TestRunner {
}
