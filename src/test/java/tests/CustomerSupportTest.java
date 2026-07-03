package tests;

import dto.FeedBackFactory;
import dto.Feedback;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Selenide.title;
import static elements.Elements.*;

public class CustomerSupportTest extends BaseTest {


    Feedback customerSupportTest = FeedBackFactory.getFeedback();


    @Test
    public void checkCustomerSupportPage() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        softAssert.assertEquals(title(), titleForDashboardPage);
        calendarPage.isPageOpened();
        calendarPage.clickCustomerSupportLink();
        softAssert.assertEquals(title(), titleForCUSTOMER_SUPPORTPage);
        verifyUrl(urlCustomerSupport);
        customerSupportPage.isPageOpened();
        isTextDisplayed(CUSTOMER_SUPPORT_TITLE);
        softAssert.assertAll();
    }
}
