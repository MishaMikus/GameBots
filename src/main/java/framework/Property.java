package framework;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Property {
    private static final Logger LOGGER = Logger.getLogger(Property.class);

    public static String get(String key) throws IOException {
        Properties prop = new Properties();
        InputStream input;
        String value = null;
        if (new File("user.properties").exists()) {
            input = new FileInputStream("user.properties");
            prop.load(input);
            value = prop.getProperty(key);
            input.close();
        }
        if (value == null) {
            value = getFromSysEnv(key);
        }
        LOGGER.info(key + " = " + value);
        return value;
    }

    public static String getFromSysEnv(String key) throws IOException {
        return System.getenv(key);
    }
}
