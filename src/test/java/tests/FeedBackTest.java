package tests;


import dto.FeedBackFactory;
import dto.Feedback;
import dto.Register;
import dto.RegisterFactory;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Selenide.title;
import static constants.ConstantElements.email;
import static constants.ConstantElements.password;
import static elements.Elements.*;

public class FeedBackTest extends BaseTest {


    Feedback feedbackTest = FeedBackFactory.getFeedback();

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Feature("FeedBack component")
    public void checkFeedBackPage() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        softAssert.assertEquals(title(), titleForDashboardPage);
        calendarPage.isPageOpened();
        calendarPage.ckickGiveFeedBackLink();
        softAssert.assertEquals(title(), titleForFeedbackPage);
        verifyUrl(urlFeedback);
        feedbackPage.isPageOpened();
        isTextDisplayed("Provide Feedback");
        softAssert.assertAll();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Feature("FeedBack component")
    public void checkSendFeedBack() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        softAssert.assertEquals(title(), titleForDashboardPage);
        calendarPage.isPageOpened();
        calendarPage.ckickGiveFeedBackLink();
        softAssert.assertEquals(title(), titleForFeedbackPage);
        verifyUrl(urlFeedback);
        feedbackPage.isPageOpened();
        isTextDisplayed(FEEDBACK_TITLE);
        feedbackPage.fillFeedback(feedbackTest)
                        .pushFeedbackButton();
        isTextDisplayed(MESSAGE_FEEDBACK);
        softAssert.assertAll();
    }
}
