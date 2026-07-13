package tests;

import dto.DailyVitals;
import dto.DailyVitalsFactory;
import dto.FeedBackFactory;
import dto.Feedback;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Selenide.title;
import static constants.ConstantElements.email;
import static constants.ConstantElements.password;
import static elements.Elements.*;

public class DailyVitalsTest extends BaseTest{

    @Test(testName = "Выбор даты для показателей"
            ,  description = "Дата выбирается через календарь ")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("DailyVitals component")
    public void checkSelectDate() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        dailyVitalsPage.openPage()
                .isPageOpened()
                        .selectRandomTimePeriod();
        verifyUrl(urlDailyVitals);
        softAssert.assertEquals(title(), titleForDailyVitalsPage);
        isTextDisplayed(DAILY_VITALS_TITLE);
        softAssert.assertAll();
    }

    @Test(testName = "Добавление ежедневных показателей"
            ,  description = "Поля Weight, Resting HR, Sleep Hours и др. заполняются и сохраняются ")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("DailyVitals component")
    public void checkAddVital() {
        SoftAssert softAssert = new SoftAssert();
        DailyVitals dailyVitals = DailyVitalsFactory.getDailyVitals();
        String expectedHealthNotes = dailyVitals.getHealthNotes();
        loginPage.openPage();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        dailyVitalsPage.openPage()
                .isPageOpened()
                        .clickAddVitalButton()
                                .fillVital(dailyVitals)
                                        .clickSaveVitalButton();
        isTextDisplayed(expectedHealthNotes);
        softAssert.assertAll();
    }

    @Test(testName = "Редактирование записи показателей"
            ,  description = "После подтверждения запись отредактирована")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("DailyVitals component")
    public void checkEdditVital() {
        SoftAssert softAssert = new SoftAssert();
        DailyVitals dailyVitals = DailyVitalsFactory.getDailyVitals();
        String expectedHealthNotes = dailyVitals.getHealthNotes();
        loginPage.openPage();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        dailyVitalsPage.openeditedPage()
                .editVital(dailyVitals)
                .clickSaveVitalButton();
        isTextDisplayed(expectedHealthNotes);
        softAssert.assertAll();
    }

    @Test(testName = "График изменения веса за период"
            ,  description = "График отображает динамику веса по дням")
    @Severity(SeverityLevel.MINOR)
    @Feature("DailyVitals component")
    public void checkWeightChange() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        dailyVitalsPage.openPage()
                .isPageOpened()
                .clickIconsExceptWeight();
        softAssert.assertAll();
    }

    @Test(testName = "График изменения HR за период"
            ,  description = "рафик отображает динамику Resting HR по дням ")
    @Severity(SeverityLevel.MINOR)
    @Feature("DailyVitals component")
    public void checkRestingHRChange() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        dailyVitalsPage.openPage()
                .isPageOpened()
                .clickIconsExceptRestingHR();
        softAssert.assertAll();
    }
}
