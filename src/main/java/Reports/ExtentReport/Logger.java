package Reports.ExtentReport;

import java.util.ArrayList;
import java.util.List;

public final class Logger {


    private static ThreadLocal<Logger> loggerThreadLocal = new ThreadLocal<>();
    public String loggerClickTxt = "";
    public String loggerNavigateTxt = "";
    public String loggerValueTxt = "";
    private List<String> listCollection = getFillDate();

    public static Logger getInstance() {
        if (loggerThreadLocal.get() == null) {
            synchronized (Logger.class) {
                loggerThreadLocal.set(new Logger());
            }
        }
        return loggerThreadLocal.get();
    }

    public List<String> getFillDate() {
        if (listCollection == null) {
            return new ArrayList<>();
        } else {
            return listCollection;
        }
    }

}
