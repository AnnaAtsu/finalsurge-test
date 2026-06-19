package tests;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Selenide.title;
import static elements.Elements.email;
import static elements.Elements.password;

@Slf4j
public class LoginTest extends BaseTest {

     @Test(description = "Успешная авторизация пользователя")
    public void checkSuccessfullLogin() {
         SoftAssert softAssert = new SoftAssert();
         loginPage.openPage();
         loginPage.isPageOpened();
         loginPage.enterCreds(email, password);
         loginPage.pushLoginButton();
         String expectedTitle = "Final Surge - Training Calendar";
         softAssert.assertEquals(title(), expectedTitle);
         softAssert.assertAll();
     }

     @Test(description = "Авторизация с пустым полем login")
    public void checkLoginWithEmptyLogin() {
         loginPage.openPage();
         loginPage.isPageOpened();
         loginPage.enterCreds("", password);
         loginPage.pushLoginButton();
         loginPage.verifyEmailError();
     }

    @Test(description = "Авторизация с пустым полем password")
    public void checkLoginWithEmptyPassword() {
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, "");
        loginPage.pushLoginButton();
        loginPage.verifyPasswordError();
    }

    @Test(description = "Авторизация с невалидным полем login")
    public void checkLoginWithUnvalidLogin() {
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds("atsulud3", password);
        loginPage.pushLoginButton();
        loginPage.verifyNotvalidLoginError();
    }

    @Test(description = "Авторизация с пустыми полями login и password")
    public void checkLoginWithEmptyFields() {
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds("", "");
        loginPage.pushLoginButton();
        loginPage.verifyEmailError();
        loginPage.verifyEmailError();
    }
}
