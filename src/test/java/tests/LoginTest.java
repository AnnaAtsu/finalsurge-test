package tests;

import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static elements.Elements.*;

@Log4j2
@Listeners
public class LoginTest extends BaseTest {

     @Test(description = "Успешный вход с валидными email и паролем")
    public void checkSuccessfullLogin() {
         SoftAssert softAssert = new SoftAssert();
         loginPage.openPage();
         loginPage.isPageOpened();
         loginPage.enterCreds(email, password);
         loginPage.pushLoginButton();
         softAssert.assertEquals(title(), titleForDashboardPage);
         softAssert.assertAll();
     }

     @Test(description = "Авторизация с пустым полем login. Ошибка входа: пустое поле Email")
    public void checkLoginWithEmptyLogin() {
         loginPage.openPage();
         loginPage.isPageOpened();
         loginPage.enterCreds("", password);
         loginPage.pushLoginButton();
         loginPage.verifyEmailError();
     }

    @Test(description = "Авторизация с пустым полем password. Ошибка входа: пустое поле Password")
    public void checkLoginWithEmptyPassword() {
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, "");
        loginPage.pushLoginButton();
        loginPage.verifyPasswordError();
    }

    @Test(description = "Авторизация с невалидным полем login. Ошибка входа: email без @")
    public void checkLoginWithUnvalidLogin() {
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds("atsulud3", password);
        loginPage.pushLoginButton();
        loginPage.verifyNotvalidLoginError();
    }

    @Test(description = "Авторизация с пустыми полями login и password. Ошибка входа: пустое поле Email. Ошибка входа: пустое поле Password ")
    public void checkLoginWithEmptyFields() {
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds("", "");
        loginPage.pushLoginButton();
        loginPage.verifyEmailError();
        loginPage.verifyEmailError();
    }

    @Test(description = "Авторизация с email, которого нет в системе. Сообщение об ошибке: `\"Invalid login credentials. Please try again.\"`")
    public void checkUnregisteredLogin() {
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds("atsulud3@ya.ru", password);
        loginPage.pushLoginButton();
        loginPage.verifyNotvalidLoginCredsError();
    }

    @Test(description = "Авторизация с password, которого нет в системе. Сообщение об ошибке: `\"Invalid login credentials. Please try again.\"`")
    public void checkUnregisteredPassword() {
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, "123");
        loginPage.pushLoginButton();
        loginPage.verifyNotvalidLoginCredsError();
    }

    @Test(description = "Редирект на Can/t sign in: запросить email")
    public void checkRequestNewPassword() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.clickForgotPasswordLink();
        loginPage.verifyRequestPasswordButton();
        $(byText(CANT_SIGN_IN_FORM_TITLE)).shouldBe(visible);
        String actualTitle = $$("p.heading_main.formpad").get(1).getText();
        $("#forgot-validate").shouldBe(visible, Duration.ofSeconds(5));
        softAssert.assertEquals(title(), titleForLoginPage);
        softAssert.assertEquals(actualTitle, titleForRequestNewPasswordPage);
        softAssert.assertAll();
    }

    @Test(description = "Ссылка sign in. Проверка перехода на страницу регистрации")
    public void checkRegistration() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.notRegisteredLinkClick();
        registerPage.isPageOpened();
        softAssert.assertEquals(title(), titleForCreateNewAccount);
        softAssert.assertAll();
    }

    @Test(description = "Разлогин: успешный логаут. Редирект на Account Login, отображается страница логина")
    public void checkSuccessfullLogout() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        softAssert.assertEquals(title(), titleForDashboardPage);
        loginPage.logoutButtonClick();
        $(byText(LOGIN_FORM_TITLE)).shouldBe(visible);
        softAssert.assertEquals(title(), titleForLogoutPage);
        softAssert.assertAll();
    }

    @Test(description = "Чекбокс Remember me: отображается, не прожат. После клика прожат")
    public void loginWithRememberMeCheckbox() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.isCheckBoxRememberMeNotSelected();
        loginPage.rememberMeCheckboxClick();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        softAssert.assertEquals(title(), titleForDashboardPage);
        softAssert.assertAll();

    }
}
