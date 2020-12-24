package Reports;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.Properties;

public class Log4J {


    public static Logger logger(Class mYclass) {
        Properties properties = new Properties();
        PropertyConfigurator.configure(properties);
        return Logger.getLogger(mYclass);
    }
}
