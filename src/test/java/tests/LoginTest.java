package tests;

import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static elements.Elements.*;
import static io.qameta.allure.internal.shadowed.jackson.databind.cfg.CoercionAction.Fail;

@Log4j2
public class LoginTest extends BaseTest {

     @Test(testName = "Успешная авторизация", description = "Успешный вход с валидными email и паролем")
    public void checkSuccessfullLogin() {
         SoftAssert softAssert = new SoftAssert();
         loginPage.openPage();
         loginPage.isPageOpened();
         loginPage.enterCreds(email, password);
         loginPage.pushLoginButton();
         verifyUrl(urlCalendar);
         softAssert.assertEquals(title(), titleForDashboardPage);
         softAssert.assertAll();
     }

     @Test(testName = "Авторизация с пустым полем логина", description = "Авторизация с пустым полем login. Ошибка входа: пустое поле Email")
    public void checkLoginWithEmptyLogin() {
         SoftAssert softAssert = new SoftAssert();
         loginPage.openPage();
         loginPage.isPageOpened();
         loginPage.enterCreds("", password);
         loginPage.pushLoginButton();
         loginPage.verifyEmailError();
         verifyUrl(urlLogin);
         softAssert.assertEquals(title(), titleForLoginPage);
         softAssert.assertAll();
     }

    @Test(testName = "Авторизация с пустым полем пароля",description = "Авторизация с пустым полем password. Ошибка входа: пустое поле Password")
    public void checkLoginWithEmptyPassword() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, "");
        loginPage.pushLoginButton();
        loginPage.verifyPasswordError();
        verifyUrl(urlLogin);
        softAssert.assertEquals(title(), titleForLoginPage);
        softAssert.assertAll();
    }

    @Test(testName = "Авторизация с невалидным полем login",description = "Авторизация с невалидным полем login. Ошибка входа: email без @")
    public void checkLoginWithUnvalidLogin() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds("atsulud3", password);
        loginPage.pushLoginButton();
        loginPage.verifyNotvalidLoginError();
        verifyUrl(urlLogin);
        softAssert.assertEquals(title(), titleForLoginPage);
        softAssert.assertAll();
    }

    @Test(testName = "Авторизация с пустыми полями login и password", description = "Авторизация с пустыми полями login и password. Ошибка входа: пустое поле Email. Ошибка входа: пустое поле Password ")
    public void checkLoginWithEmptyFields() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds("", "");
        loginPage.pushLoginButton();
        loginPage.verifyEmailError();
        loginPage.verifyEmailError();
        verifyUrl(urlLogin);
        softAssert.assertEquals(title(), titleForLoginPage);
        softAssert.assertAll();
    }

    @Test(testName = "Авторизация с email, которого нет в системе", description = "Авторизация с email, которого нет в системе. Сообщение об ошибке: `\"Invalid login credentials. Please try again.\"`")
    public void checkUnregisteredLogin() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds("atsulud3@ya.ru", password);
        loginPage.pushLoginButton();
        loginPage.verifyNotvalidLoginCredsError();
        verifyUrl(urlLogin);
        softAssert.assertEquals(title(), titleForLoginPage);
        softAssert.assertAll();
    }

    @Test(testName = "Авторизация с password, которого нет в системе",description = "Авторизация с password, которого нет в системе. Сообщение об ошибке: `\"Invalid login credentials. Please try again.\"`")
    public void checkUnregisteredPassword() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, "123");
        loginPage.pushLoginButton();
        loginPage.verifyNotvalidLoginCredsError();
        verifyUrl(urlLogin);
        softAssert.assertEquals(title(), titleForLoginPage);
        softAssert.assertAll();
    }

    @Test(testName = "Редирект на Can/t sign in", description = "Редирект на Can/t sign in: запросить email")
    public void checkRequestNewPassword() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.clickForgotPasswordLink();
        loginPage.verifyRequestPasswordButton();
        $(byText(CANT_SIGN_IN_FORM_TITLE)).shouldBe(visible);
        String actualTitle = $$("p.heading_main.formpad").get(1).getText();
        $("#forgot-validate").shouldBe(visible, Duration.ofSeconds(5));
        verifyUrl(urlLogin);
        softAssert.assertEquals(title(), titleForLoginPage);
        softAssert.assertEquals(actualTitle, titleForRequestNewPasswordPage);
        softAssert.assertAll();
    }

    @Test(testName = "Редирект по ссылке sign in", description = "Ссылка sign in. Проверка перехода на страницу регистрации")
    public void checkRegistration() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.notRegisteredLinkClick();
        registerPage.isPageOpened();
        verifyUrl(urlRegister);
        softAssert.assertEquals(title(), titleForCreateNewAccount);
        softAssert.assertAll();
    }

    @Test(testName = "Успешная проверка разлогина", description = "Разлогин: успешный логаут. Редирект на Account Login, отображается страница логина")
    public void checkSuccessfullLogout() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        softAssert.assertEquals(title(), titleForDashboardPage);
        loginPage.logoutButtonClick();
        $(byText(LOGIN_FORM_TITLE)).shouldBe(visible);
        verifyUrl(urlLogout);
        softAssert.assertEquals(title(), titleForLogoutPage);
        softAssert.assertAll();
    }

    @Test(testName = "Проверка чекбокса Remember me: отображается, не прожат", description = "Чекбокс Remember me: отображается, не прожат. После клика прожат")
    public void loginWithRememberMeCheckbox() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.isCheckBoxRememberMeNotSelected();
        loginPage.rememberMeCheckboxClick();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        verifyUrl(urlRegister);
        softAssert.assertEquals(title(), titleForDashboardPage);
        softAssert.assertAll();

    }
}
