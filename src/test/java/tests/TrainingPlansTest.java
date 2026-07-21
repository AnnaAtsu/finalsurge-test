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
import static urls.Urls.urlTrainingPlans;
import static urls.Urls.urlTrainingPlansHistory;

public class TrainingPlansTest extends BaseTest {


    @Test(testName = "Отображение списка доступных планов "
            , description = "Список содержит карточки планов с названием, длительностью и кратким описанием")
    @Severity(SeverityLevel.NORMAL)
    @Feature("TrainingPlans component")
    public void checkTrainingPlansPage() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        trainingPlansPage.openPage()
                .isPageOpened();
        softAssert.assertEquals(title(), titleForTRainingPlansPage);
        urlAssertion.verifyUrl(urlTrainingPlans);
        textAssertion.isTextDisplayed(TRAINING_PLAN_MESSAGE);
    }

    @Test(testName = "Просмотр деталей планов  "
            , description = " При клике открывается страница с полными описаниями, длительностью и тренировками")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("TrainingPlans component")
    public void checkTrainingPlansDetail() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        trainingPlansPage.openPage()
                .isPageOpened();
        softAssert.assertEquals(title(), titleForTRainingPlansPage);
        urlAssertion.verifyUrl(urlTrainingPlans);
        trainingPlansPage.clickFindPlanButton();
        trainingPlansPage.verifyTrainingPlans();
    }

    @Test(testName = "Просмотр деталей конкретного плана"
            , description = " При клике открывается страница с полными описаниями, длительностью и т.д.")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("TrainingPlans component")
    public void checkTrainingPlansDetaiItem() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        trainingPlansPage.openPage()
                .isPageOpened();
        softAssert.assertEquals(title(), titleForTRainingPlansPage);
        urlAssertion.verifyUrl(urlTrainingPlans);
        trainingPlansPage.clickFindHistoryPlanButton();
        urlAssertion.verifyUrl(urlTrainingPlansHistory);
        softAssert.assertEquals(title(), titleForTrainingPlansHistoryPage);
        textAssertion.isTextDisplayed(TRAINING_PLAN_MESSAGE_HISTORY);
    }
}
