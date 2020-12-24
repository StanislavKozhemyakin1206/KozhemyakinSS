package Reports.AllureReport;

import io.qameta.allure.Allure;
import io.qameta.allure.internal.AllureStorage;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;

import java.util.UUID;
import java.util.function.Consumer;


public class AllureUtils extends AllureStorage {

    private static AllureUtils instance;

    public static synchronized AllureUtils getInstance() {
        if (instance == null) {
            instance = new AllureUtils();
        }
        return instance;
    }

    public static void SetStep(String step, Status status) {
        final StepResult stepResult = new StepResult();
        final String stepUUID = UUID.randomUUID().toString();
        stepResult.setStart(System.currentTimeMillis());
        stepResult.setStop(System.currentTimeMillis());
        stepResult.setStatus(status);
        stepResult.setDescription(step);
        Allure.getLifecycle().startStep(stepUUID, stepResult);
        Consumer<StepResult> updateResult = result -> result.setName(step);
        Allure.getLifecycle().updateStep(stepUUID, updateResult);
        Allure.getLifecycle().stopStep(stepUUID);
    }

    public void createTestCases(String uuid) {
        Allure.getLifecycle().writeTestCase(uuid);
    }

    /**
     * Get ExtentScreen Allure
     *
     * @param screenName
     */
    public void addScreenAllure(String screenName, String parameter, String screen) {
        try {
            Allure.addAttachment(screenName, parameter, screen);
        } catch (NullPointerException ex) {
            System.out.println("ExtentScreen cant creat");
        }

    }

}
