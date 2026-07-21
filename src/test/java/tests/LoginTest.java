package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static constants.ConstantElements.email;
import static constants.ConstantElements.password;
import static elements.Elements.*;
import static urls.Urls.*;

@Log4j2
public class LoginTest extends BaseTest {

    @Test(testName = "Успешная авторизация", description = "Успешный вход с валидными email и паролем")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Login component")
    public void checkSuccessfullLogin() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        urlAssertion.verifyUrl(urlCalendar);
        softAssert.assertEquals(title(), titleForDashboardPage);
        softAssert.assertAll();
    }

    @Test(testName = "Авторизация с пустым полем логина", description = "Авторизация с пустым полем login. Ошибка входа: пустое поле Email")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Login component")
    public void checkLoginWithEmptyLogin() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds("", password);
        loginPage.pushLoginButton();
        loginPage.verifyEmailError();
        urlAssertion.verifyUrl(urlLogin);
        softAssert.assertEquals(title(), titleForLoginPage);
        softAssert.assertAll();
    }

    @Test(testName = "Авторизация с пустым полем пароля", description = "Авторизация с пустым полем password. Ошибка входа: пустое поле Password")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Login component")
    public void checkLoginWithEmptyPassword() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, "");
        loginPage.pushLoginButton();
        loginPage.verifyPasswordError();
        urlAssertion.verifyUrl(urlLogin);
        softAssert.assertEquals(title(), titleForLoginPage);
        softAssert.assertAll();
    }

    @Test(testName = "Авторизация с невалидным полем login", description = "Авторизация с невалидным полем login. Ошибка входа: email без @")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Login component")
    public void checkLoginWithUnvalidLogin() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds("atsulud3", password);
        loginPage.pushLoginButton();
        loginPage.verifyNotvalidLoginError();
        urlAssertion.verifyUrl(urlLogin);
        softAssert.assertEquals(title(), titleForLoginPage);
        softAssert.assertAll();
    }

    @Test(testName = "Авторизация с пустыми полями login и password", description = "Авторизация с пустыми полями login и password. Ошибка входа: пустое поле Email. Ошибка входа: пустое поле Password ")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Login component")
    public void checkLoginWithEmptyFields() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds("", "");
        loginPage.pushLoginButton();
        loginPage.verifyEmailError();
        loginPage.verifyEmailError();
        urlAssertion.verifyUrl(urlLogin);
        softAssert.assertEquals(title(), titleForLoginPage);
        softAssert.assertAll();
    }

    @Test(testName = "Авторизация с email, которого нет в системе", description = "Авторизация с email, которого нет в системе. Сообщение об ошибке: `\"Invalid login credentials. Please try again.\"`")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Login component")
    public void checkUnregisteredLogin() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds("atsulud3@ya.ru", password);
        loginPage.pushLoginButton();
        loginPage.verifyNotvalidLoginCredsError();
        urlAssertion.verifyUrl(urlLogin);
        softAssert.assertEquals(title(), titleForLoginPage);
        softAssert.assertAll();
    }

    @Test(testName = "Авторизация с password, которого нет в системе", description = "Авторизация с password, которого нет в системе. Сообщение об ошибке: `\"Invalid login credentials. Please try again.\"`")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Login component")
    public void checkUnregisteredPassword() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, "123");
        loginPage.pushLoginButton();
        loginPage.verifyNotvalidLoginCredsError();
        urlAssertion.verifyUrl(urlLogin);
        softAssert.assertEquals(title(), titleForLoginPage);
        softAssert.assertAll();
    }

    @Test(testName = "Редирект на Can/t sign in", description = "Редирект на Can/t sign in: запросить email")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Login component")
    public void checkRequestNewPassword() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.clickForgotPasswordLink();
        loginPage.verifyRequestPasswordButton();
        $(byText(CANT_SIGN_IN_FORM_TITLE)).shouldBe(visible);
        String actualTitle = $$("p.heading_main.formpad").get(1).getText();
        $("#forgot-validate").shouldBe(visible, Duration.ofSeconds(5));
        urlAssertion.verifyUrl(urlLogin);
        softAssert.assertEquals(title(), titleForLoginPage);
        softAssert.assertEquals(actualTitle, titleForRequestNewPasswordPage);
        softAssert.assertAll();
    }

    @Test(testName = "Редирект по ссылке sign in", description = "Ссылка sign in. Проверка перехода на страницу регистрации")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Login component")
    public void checkRegistration() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.notRegisteredLinkClick();
        registerPage.isPageOpened();
        urlAssertion.verifyUrl(urlRegister);
        softAssert.assertEquals(title(), titleForCreateNewAccount);
        softAssert.assertAll();
    }

    @Test(testName = "Успешная проверка разлогина", description = "Разлогин: успешный логаут. Редирект на Account Login, отображается страница логина")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Login component")
    public void checkSuccessfullLogout() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        softAssert.assertEquals(title(), titleForDashboardPage);
        loginPage.logoutButtonClick();
        $(byText(LOGIN_FORM_TITLE)).shouldBe(visible);
        urlAssertion.verifyUrl(urlLogout);
        softAssert.assertEquals(title(), titleForLogoutPage);
        softAssert.assertAll();
    }

    @Test(testName = "Проверка чекбокса Remember me: отображается, не прожат", description = "Чекбокс Remember me: отображается, не прожат. После клика прожат")
    @Severity(SeverityLevel.MINOR)
    @Feature("Login component")
    public void loginWithRememberMeCheckbox() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.isCheckBoxRememberMeNotSelected();
        loginPage.rememberMeCheckboxClick();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        urlAssertion.verifyUrl(urlCalendar);
        softAssert.assertEquals(title(), titleForDashboardPage);
        softAssert.assertAll();
    }
}
