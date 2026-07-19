package tests;

import assertions.TextAssertion;
import assertions.UrlAssertion;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.qameta.allure.testng.AllureTestNg;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.ITestResult;
import pages.*;
import listeners.TestListener;
import utils.AllureUtils;

import java.util.HashMap;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
@Log4j2
@Listeners({TestListener.class, AllureTestNg.class})
public class BaseTest {
    LoginPage loginPage;
    RegisterPage registerPage;
    DashboardPage dashboardPage;
    CalendarPage calendarPage;
    FeedbackPage feedbackPage;
    WorkoutsPage workoutsPage;
    CustomerSupportPage customerSupportPage;
    WorkoutReportPage workoutReportPage;
    UserProfilePage userProfilePage;
    DailyVitalsPage dailyVitalsPage;
    TextAssertion textAssertion;
    UrlAssertion urlAssertion;
    TrainingPlansPage trainingPlansPage;

    @BeforeMethod
    public void setup() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://log.finalsurge.com";
        Configuration.timeout = 10000;
        Configuration.clickViaJs = true;
        Configuration.browserSize = "1920x1080";

        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        //добавила строку ниже для github actions
        options.addArguments("--headless");
        Configuration.browserCapabilities = options;

        loginPage = new LoginPage();
        registerPage = new RegisterPage();
        dashboardPage = new DashboardPage();
        calendarPage = new CalendarPage();
        feedbackPage = new FeedbackPage();
        workoutsPage = new WorkoutsPage();
        customerSupportPage = new CustomerSupportPage();
        workoutReportPage = new WorkoutReportPage();
        userProfilePage = new UserProfilePage();
        dailyVitalsPage = new DailyVitalsPage();
        textAssertion = new TextAssertion();
        urlAssertion = new UrlAssertion();
        trainingPlansPage = new TrainingPlansPage();

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
        );
    }

    @AfterMethod
    public void quit(ITestResult result) {
        if (!result.isSuccess()) {
            AllureUtils.takeScreenshot(getWebDriver());
        }
        getWebDriver().quit();
    }
}
