package utils;

import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static ConfigReader instance;
    private final Properties properties;

    private ConfigReader() {
        properties = new Properties();
        String[] propertyFiles = {
            "config.properties",
            "credentials.properties" 
        };

        for (String filePath : propertyFiles) {
            try (InputStream input = getClass().getClassLoader().getResourceAsStream(filePath)) {
                if (input == null) {
                    System.out.println("Warning: Property file not found in classpath: " + filePath);
                    continue;
                }
                properties.load(input);
            } catch (IOException ex) {
                System.err.println("Error loading property file: " + filePath);
                ex.printStackTrace();
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
        if (value == null || value.trim().isEmpty()) {
            String envVarKey = key.toUpperCase().replace('.', '_');
            value = System.getenv(envVarKey);
        }
        if (value == null)
            throw new RuntimeException("Property '" + key + "' not found in environment variables or property files.");

        return value;
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
