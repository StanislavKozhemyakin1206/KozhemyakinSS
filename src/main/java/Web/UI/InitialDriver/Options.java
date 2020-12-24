package Web.UI.InitialDriver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;

import java.io.File;
import java.util.HashMap;

abstract class Options {

    ChromeOptions chromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-infobars");
        options.addArguments("--no-sandbox");

        HashMap<String, Object> prefs = new HashMap<>();
        prefs.put("plugins.always_open_pdf_externally", true);
        prefs.put("download.default_directory", System.getProperty("user.dir") + File.separator + "src"+ File.separator + "resources" + File.separator + "DownloadedPDF");
        options.setExperimentalOption("prefs", prefs);
        return options;
    }

}
