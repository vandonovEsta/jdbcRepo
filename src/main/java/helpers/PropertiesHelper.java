package helpers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesHelper {
    String configLocation = "src/main/java/resources/config.properties";
    Properties prop;
    FileInputStream input;

    public PropertiesHelper() throws IOException {
        prop = new Properties();
        input = new FileInputStream(configLocation);
        prop.load(input);
    }

    public String readProperty(String propName) throws IOException {
        if (input != null) {
            prop.load(input);
            if (prop.getProperty(propName) != null) {
                return prop.getProperty(propName);
            } else {
                return "No such property + " + propName;
            }
        } else {
            throw new FileNotFoundException("properties file " + configLocation + " is missing.");
        }
    }

}
