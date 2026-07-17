package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebElementCondition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static urls.Urls.loginEndpoint;

@Log4j2
public class LoginPage extends BasePage{

    private final SelenideElement LOGIN_FIELD = $("#login_name");
    private  final SelenideElement PASSWORD_FIELD = $("#login_password");
    private final SelenideElement LOGIN_BUTTON  =$("button[type=submit]");
    private  final SelenideElement CHECKBOX_REMEMBER_ME = $("#login_remember");
    private  final SelenideElement SIGN_UP = $(byText("Not registered?"));
    private  final SelenideElement LOGIN_ERROR = $x("//label[@class='error' and @for='login_name']");
    private final SelenideElement PASSWORD_ERROR = $x("//label[@class='error' and @for='login_password']");
    private final String LOGIN_FORM_TITLE = "Account Login";
    private final SelenideElement INVALID_LOGIN_CREDS_ERROR = $(".alert.alert-error strong");
    private final SelenideElement FORGOT_PASSWORD_LINK = $("#pass_login");
    private final SelenideElement REQUEST_NEW_PASSWORD_BUTTON = $x("//button[normalize-space()='Request New Password']");
    private final SelenideElement LOGOUT_BUTTON = $x("//a[text()='Logout']");


    @Step("Открыть страницу авторизации")
    public LoginPage openPage() {
        log.info("Открыть страницу авторизации");
        Selenide.open(loginEndpoint);
        return this;
    }


    @Override
    @Step("Проверка, открыта ли страница авторизации")
    public LoginPage isPageOpened() {
        log.info("Проверка, открыта ли страница авторизации");
        $(byText(LOGIN_FORM_TITLE)).shouldBe(visible);
        LOGIN_BUTTON.should(visible);
        LOGIN_FIELD.should(visible);
        PASSWORD_FIELD.should(visible);
        SIGN_UP.shouldBe(visible);
        FORGOT_PASSWORD_LINK.shouldBe(visible);
        CHECKBOX_REMEMBER_ME.should(visible);
        return this;
    }

    @Step("Ввести креды для авторизации")
    public void enterCreds(String email, String password) {
        log.info("Ввести креды для авторизации");
        LOGIN_FIELD.sendKeys(email);
        PASSWORD_FIELD.sendKeys(password);

    }

    @Step("Нажать кнопку логина")
    public void pushLoginButton() {
        log.info("Нажать кнопку логина");
        LOGIN_BUTTON.click();
    }

    @Step("Проверка ошибки email")
    public void verifyEmailError() {
        log.info("Проверка ошибки email");
        LOGIN_ERROR.should(visible);
        LOGIN_ERROR.shouldHave(text("Please enter your e-mail address."));
    }

    @Step("Проверка ошибки пароля")
    public void verifyPasswordError() {
        log.info("Проверка ошибки пароля");
        PASSWORD_ERROR.should(visible);
        PASSWORD_ERROR.shouldHave(text("Please enter a password."));
    }

    @Step("Проверка ошибки валидности email")
    public void verifyNotvalidLoginError() {
        log.info("Проверка ошибки валидности email");
        LOGIN_ERROR.should(visible);
        LOGIN_ERROR.shouldHave(text("Please enter a valid email address."));
    }

    @Step("Проверка ошибки невалидных кредов")
    public void verifyNotvalidLoginCredsError() {
        log.info("Проверка ошибки невалидных кредов");
        INVALID_LOGIN_CREDS_ERROR.should(visible);
        INVALID_LOGIN_CREDS_ERROR.shouldHave(text("Invalid login credentials. Please try again."));
    }
    @Step("Нажать на ссылку Forgot password")
    public  void clickForgotPasswordLink() {
        log.info("Нажать на ссылку Forgot password");
        FORGOT_PASSWORD_LINK.shouldBe(visible, Duration.ofSeconds(5));
        FORGOT_PASSWORD_LINK.click();
    }

    @Step("Проверка кнопки веридикации кредов")
    public void verifyRequestPasswordButton() {
        log.info("Проверка кнопки веридикации кредов");
        REQUEST_NEW_PASSWORD_BUTTON.shouldBe(visible);
        REQUEST_NEW_PASSWORD_BUTTON.shouldHave(text("Request New Password"));
    }

    @Step("Нажать на ссылку Logout")
    public void logoutButtonClick() {
        log.info("Нажать на ссылку Logout");
        LOGOUT_BUTTON.shouldBe(visible, Duration.ofSeconds(5));
        LOGOUT_BUTTON.click();
    }

    @Step("Нажать на ссылку Not registered?")
    public void notRegisteredLinkClick() {
        log.info("Нажать на ссылку Not registered?");
        SIGN_UP.shouldBe(visible, Duration.ofSeconds(5));
        SIGN_UP.click();
    }

    @Step("Нажать чекбок Remember me")
    public void rememberMeCheckboxClick() {
        log.info("Нажать чекбок Remember me");
        CHECKBOX_REMEMBER_ME.shouldBe(visible);
        CHECKBOX_REMEMBER_ME.click();
        CHECKBOX_REMEMBER_ME.shouldBe(checked);
    }

    @Step("Проверка выбора чекбокса Remember me")
    public void isCheckBoxRememberMeNotSelected() {
        log.info("Проверка выбора чекбокса Remember me");
        SoftAssert softAssert = new SoftAssert();
        CHECKBOX_REMEMBER_ME.shouldBe(visible);
        softAssert.assertFalse(CHECKBOX_REMEMBER_ME.isSelected());
        softAssert.assertAll();
    }
}
