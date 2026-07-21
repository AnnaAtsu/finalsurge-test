package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Selenide.title;
import static constants.ConstantElements.email;
import static constants.ConstantElements.password;
import static elements.Elements.*;
import static urls.Urls.urlFeedback;

public class CustomerSupportTest extends BaseTest {


    @Test(testName = "Переход на страницу поддержки", description = "Проверка отображения страницы")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("CustomerSupport component")
    public void checkCustomerSupportPage() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        softAssert.assertEquals(title(), titleForDashboardPage);
        calendarPage.isPageOpened();
        calendarPage.clickCustomerSupportLink();
        softAssert.assertEquals(title(), titleForFeedbackPage);
        urlAssertion.verifyUrl(urlFeedback);
        customerSupportPage.isPageOpened();
        textAssertion.isTextDisplayed(CUSTOMER_SUPPORT_TITLE);
        softAssert.assertAll();
    }
}
