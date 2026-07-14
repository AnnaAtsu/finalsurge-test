package tests;

import dto.Register;
import dto.RegisterFactory;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import static com.codeborne.selenide.Selenide.title;
import static elements.Elements.*;
import static urls.Urls.*;

@Log4j2
public class RegisterTest extends BaseTest {

    Register registerTestSuccessfullRegistration = RegisterFactory.getRegister("Texts56456!");
    Register registerTestforWeakPassword = RegisterFactory.getRegister("Te");
    Register registerTestForEmailValidation = new Register("John", "Doe", "jjfjg@", "Texts56456!");

    @Test(testName = "Успешная регистрация в системе", description = "Успешная регистрация в системе")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Register component")
    public void checkSuccessfullRegistration() {
        SoftAssert softAssert = new SoftAssert();
        registerPage.openPage()
                .isPageOpened();
        registerPage.createAccount(registerTestSuccessfullRegistration);
        registerPage.pushRegisterButton();
        urlAssertion.verifyUrl(urlCalendar);
        softAssert.assertEquals(title(), titleForDashboardPage);
        softAssert.assertAll();
    }

    @Test(testName = "Ввалидация некорректного email", description = "Проверка валидации некорректного email")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Register component")
    public void checkValidMessageForEmailForRegistration() {
        SoftAssert softAssert = new SoftAssert();
        registerPage.openPage()
                     .isPageOpened();
        registerPage.createAccount(registerTestForEmailValidation);
        registerPage.pushRegisterButton();
        registerPage.verifyInvalidEmailErrorMessage();
        urlAssertion.verifyUrl(urlRegister);
        softAssert.assertEquals(title(), titleForCreateNewAccount);
        softAssert.assertAll();
    }

    @Test(testName = "Ввалидация пустых полей", description = "Проверка валидации пустых полей")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Register component")
    public void checkValidMessagesForEmptyRegistration() {
        SoftAssert softAssert = new SoftAssert();
        registerPage.openPage()
                .isPageOpened();
        registerPage.pushRegisterButton();
        registerPage.verifyEmptyFieldsErrorMessage();
        urlAssertion.verifyUrl(urlRegister);
        softAssert.assertEquals(title(), titleForCreateNewAccount);
        softAssert.assertAll();
    }

    @Test(testName = "Валидация слабого пароля", description = "Проверка валидации слабого пароля")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Register component")
    public void checkValidMessageForWeakPassword() {
        SoftAssert softAssert = new SoftAssert();
        registerPage.openPage()
                .isPageOpened();
        registerPage.createAccount(registerTestforWeakPassword);
        registerPage.pushRegisterButton();
        registerPage.verifyWeakPasswordErrorMessage();
        urlAssertion.verifyUrl(urlRegister);
        softAssert.assertEquals(title(), titleForCreateNewAccount);
        softAssert.assertAll();
    }

    @Test(testName = "Проверка перехода на страницу логина по ссылке по страницы регистрации", description = "Проверка перехода на страницу логина")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Register component")
    public void checkLoginLink() {
        SoftAssert softAssert = new SoftAssert();
        registerPage.openPage()
                .isPageOpened();
        registerPage.clickLoginLink();
        loginPage.isPageOpened();
        urlAssertion.verifyUrl(urlLogin);
        softAssert.assertEquals(title(), titleForLoginPage);
        softAssert.assertAll();
    }
}
