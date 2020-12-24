package Web.Utility;

import Web.UI.InitialDriver.InitialDriver;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.HashMap;

public class ExtentScreen {

    private WebDriver driver= InitialDriver.getInstance().getDriver();

    public void getScreenNewVersion(HashMap<String, String> allData)
    {
        try {
            if (driver == null) return;
            System.out.println(allData.get("path"));
            Reports.ExtentReport_4.ExtentTestManager.getInstance().getLoger().addScreencastFromPath(allData.get("path"));

        }
        catch (IOException ex)
        {
			System.out.println(ex.getMessage());
        }

    }
}
