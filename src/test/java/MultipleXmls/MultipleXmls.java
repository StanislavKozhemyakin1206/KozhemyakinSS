package MultipleXmls;

import Web.UI.InitialDriver.InitialDriver;
import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.xml.Parser;
import org.testng.xml.XmlSuite;

import java.io.IOException;
import java.util.List;

public class MultipleXmls extends InitialDriver {

    @Test
    public void multipleXmls() throws IOException {
        TestNG testng = new TestNG();
        testng.setXmlSuites((List<XmlSuite>)(new Parser("src\\test\\resources\\test-yandex-market-two-threads.xml").parse()));
        testng.setSuiteThreadPoolSize(2);
        testng.run();
    }
}
