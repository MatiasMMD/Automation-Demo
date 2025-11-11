package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static ConfigReader instance;
    private final Properties properties;

    private ConfigReader() {
        properties = new Properties();
        String[] propertyFiles = {
            "src/main/resources/config.properties",
            "src/main/resources/credentials.properties" 
        };

        for (String filePath : propertyFiles) {
            try (FileInputStream fis = new FileInputStream(filePath)) {
                properties.load(fis);
            } catch (IOException e) {
                System.out.println("Warning: Property file not found: " + filePath + ". This might be expected for credentials.properties in some environments.");
            }
        }
    }

    public static synchronized ConfigReader getInstance() {
        if (instance == null) {
            instance = new ConfigReader();
        }
        return instance;
    }

    public String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value != null) {
            return value;
        } else {
            throw new RuntimeException("Property '" + key + "' not specified in the config.properties file.");
        }
    }

    public String getBrowser() { return getProperty("browser"); }
    public String getBaseUrl() { return getProperty("baseUrl"); }
    public long getImplicitWait() { return Long.parseLong(getProperty("implicitWait")); }
    public long getExplicitWait() { return Long.parseLong(getProperty("explicitWait")); }

    public String getUsername() { return getProperty("username"); }
    public String getPassword() { return getProperty("password"); }
    public String getName() { return getProperty("name"); }
    public String getCountry() { return getProperty("country"); }
    public String getCity() { return getProperty("city"); }
    public String getCreditCard() { return getProperty("creditcard"); }
    public String getMonth() { return getProperty("month"); }
    public String getYear() { return getProperty("year"); }
}
