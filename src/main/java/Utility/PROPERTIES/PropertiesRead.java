package Utility.PROPERTIES;

import java.io.IOException;
import java.io.*;
import java.util.Properties;


public class PropertiesRead {


    public String getPropValues(String name, String propertiesFileName) {
        String result = "";
        String myProject = System.getProperty("user.dir");
        try {
            String s = myProject + "/src/resources/" + propertiesFileName;
            InputStream input = new FileInputStream(s);
            Properties prop = new Properties();
            prop.load(input);
            result = prop.getProperty(name);
        } catch (IOException e) {
            System.out.println("Exception: " + e);
        }
        return result;
    }
}
