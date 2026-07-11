package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Selenide.title;
import static constants.ConstantElements.email;
import static constants.ConstantElements.password;
import static elements.Elements.*;

public class DailyVitalsTest extends BaseTest{

    @Test(testName = "Выбор даты для показателей"
            ,  description = "Дата выбирается через календарь ")
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
    public void addVital() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        dailyVitalsPage.openPage()
                .isPageOpened()
                        .clickAddVitalButton();
        //дописать
        softAssert.assertAll();
    }
}
