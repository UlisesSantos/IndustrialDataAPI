package com.IndustrialDataAPI.common;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.ReloadingFileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.reloading.PeriodicReloadingTrigger;
import org.apache.commons.configuration2.reloading.ReloadingController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public final class App {

    private static final Logger LOGGER = LogManager.getLogger(App.class);
    private static final String APPLICATION_PROPERTIES = "application.properties";
    private static final String APPLICATION_NAME_KEY = "spring.application.name";
    private static final String INSTANCE_NAME_KEY = "INSTANCE_NAME";
    private static final String LOGS_PATH_KEY = "LOGS_PATH";
    private static final String PROPERTIES_PATH_KEY = "PROPERTIES_PATH";
    private static final String SECRET_KEY_KEY = "SECRET_KEY";

    private static PropertiesConfiguration configuration;
    static {
        try{
            Parameters params = new Parameters();
            ReloadingFileBasedConfigurationBuilder<PropertiesConfiguration> builder =
                    new ReloadingFileBasedConfigurationBuilder<>(PropertiesConfiguration.class)
                            .configure(params.properties()
                                    .setFileName(APPLICATION_PROPERTIES)
                                    .setEncoding(StandardCharsets.UTF_8.name())
                                    .setThrowExceptionOnMissing(true)
                                    .setListDelimiterHandler(null));
            ReloadingController controller = builder.getReloadingController();
            PeriodicReloadingTrigger trigger = new PeriodicReloadingTrigger(
                    controller,
                    null,
                    5,
                    TimeUnit.SECONDS
            );
            trigger.start();
            configuration = builder.getConfiguration();

        } catch (Exception e) {
            LOGGER.error("Could not get the properties");
        }
    }

    /**
     * Function to get a property by key
     * @param key Key of the property
     * @return property
     */
    public static String getProperty(String key) {return configuration.getString(key);}

    public static final String APPLICATION_NAME = getProperty(APPLICATION_NAME_KEY);
    public static final String SECRET_KEY = System.getProperty(SECRET_KEY_KEY);
    public static final String LOGS_PATH = System.getProperty(LOGS_PATH_KEY);
    public static final String INSTANCE_NAME = System.getProperty(INSTANCE_NAME_KEY);
    public static final String PROPERTIES_PATH = System.getProperty(PROPERTIES_PATH_KEY);
}
