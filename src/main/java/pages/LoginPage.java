package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

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


    public LoginPage openPage() {
        log.info("Открыть страницу авторизации");
        Selenide.open("/login.cshtml");
        return this;
    }


    @Override
    public LoginPage isPageOpened() {
        log.info("Проверка, открыта ли страница авторизации");
        $(byText(LOGIN_FORM_TITLE)).shouldBe(visible);
        LOGIN_BUTTON.should(visible);
        LOGIN_FIELD.should(visible);
        PASSWORD_FIELD.should(visible);
        SIGN_UP.shouldBe(visible);
        CHECKBOX_REMEMBER_ME.should(visible);
        return this;
    }

    public void enterCreds(String email, String password) {
        log.info("Ввести креды для авторизации");
        LOGIN_FIELD.sendKeys(email);
        PASSWORD_FIELD.sendKeys(password);

    }

    public void pushLoginButton() {
        log.info("Нажать кнопку логина");
        LOGIN_BUTTON.click();
    }

    public void verifyEmailError() {
        LOGIN_ERROR.should(visible);
        LOGIN_ERROR.shouldHave(text("Please enter your e-mail address."));
    }

    public void verifyPasswordError() {
        PASSWORD_ERROR.should(visible);
        PASSWORD_ERROR.shouldHave(text("Please enter a password."));
    }

    public void verifyNotvalidLoginError() {
        LOGIN_ERROR.should(visible);
        LOGIN_ERROR.shouldHave(text("Please enter a valid email address."));
    }

}
