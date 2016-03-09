package framework;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Property {
    public static String get(String key) throws IOException {
        Properties prop = new Properties();
        InputStream input;
        input = new FileInputStream("user.properties");
        prop.load(input);
        String value = prop.getProperty(key);
        input.close();
        return value;
    }
}
