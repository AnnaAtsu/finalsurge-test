package tests;


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
import static urls.Urls.urlFeedback;

public class FeedBackTest extends BaseTest {

    Feedback feedbackTest = FeedBackFactory.getFeedback();

    @Test(testName = "Переход по странице фидбека", description = "Отображается страница фидбека и текст Provide Feedback")
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
        urlAssertion.verifyUrl(urlFeedback);
        feedbackPage.isPageOpened();
        textAssertion.isTextDisplayed("Provide Feedback");
        softAssert.assertAll();
    }

    @Test(testName = "Успешная отправка фидбека", description = "Отображается сообщение об успешной отправке фидбека")
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
        urlAssertion.verifyUrl(urlFeedback);
        feedbackPage.isPageOpened();
        textAssertion.isTextDisplayed(FEEDBACK_TITLE);
        feedbackPage.fillFeedback(feedbackTest)
                .pushFeedbackButton();
        textAssertion.isTextDisplayed(MESSAGE_FEEDBACK);
        softAssert.assertAll();
    }
}
