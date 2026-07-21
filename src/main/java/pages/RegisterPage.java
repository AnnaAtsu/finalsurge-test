package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import dto.Register;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static elements.Elements.REGISTRATION_FORM_TITLE;
import static urls.Urls.registerEndpoint;

@Log4j2
public class RegisterPage extends BasePage {
    private final SelenideElement ALREADY_HAVE_ACCOUNT_LINK = $(byText("Already have an account? Login here."));
    private final SelenideElement CREATE_NEW_ACCOUNT_BUTTON = $("button[type=\"submit\"]");
    private final SelenideElement FIRST_NAME_FIELD = $("#create_first");
    private final SelenideElement LAST_NAME_FIELD = $("#create_last");
    private final SelenideElement EMAIL_FIELD = $("#create_email");
    private final SelenideElement PASSWORD_FIELD = $("#password_meter");
    private final SelenideElement RE_PASSWORD_FIELD = $("#create_passwordmatch");
    private final SelenideElement EMPTY_FIELD_FIRST_NAME_ERROR_MESSAGE = $x("//label[@class='error' and @for='create_first']");
    private final SelenideElement EMPTY_FIELD_LAST_NAME_ERROR_MESSAGE = $x("//label[@class='error' and @for='create_last']");
    private final SelenideElement EMPTY_FIELD_EMAIL_ERROR_MESSAGE = $x("//label[@class='error' and @for='create_email']");
    private final SelenideElement EMPTY_FIELD_PASSWORD_ERROR_MESSAGE = $x("//label[@class='error' and @for='password_meter']");
    private final SelenideElement EMPTY_FIELD_RE_PASSWORD_ERROR_MESSAGE = $x("//label[@class='error' and @for='create_passwordmatch']");
    private final SelenideElement WEAK_PASSWORD_ERROR_MESSAGE = $(".alert.alert-error");

    @Step("Открыть страницу регистрации")
    public RegisterPage openPage() {
        log.info("Открыть страницу регистрации");
        Selenide.open(registerEndpoint);
        return this;
    }

    @Override
    @Step("Проверка, открыта ли страница регистрации")
    public RegisterPage isPageOpened() {
        log.info("Проверка, открыта ли страница регистрации");
        $(byText(REGISTRATION_FORM_TITLE)).shouldBe(visible);
        CREATE_NEW_ACCOUNT_BUTTON.shouldBe(visible);
        ALREADY_HAVE_ACCOUNT_LINK.shouldBe(visible);
        FIRST_NAME_FIELD.shouldBe(visible);
        LAST_NAME_FIELD.shouldBe(visible);
        PASSWORD_FIELD.shouldBe(visible);
        EMAIL_FIELD.shouldBe(visible);
        RE_PASSWORD_FIELD.shouldBe(visible);
        return this;
    }

    @Step("Создание аккаунта '{}'")
    public void createAccount(Register register) {
        log.info("Создание аккаунта '{}'", register.getFirstName());
        FIRST_NAME_FIELD.sendKeys(register.getFirstName());
        LAST_NAME_FIELD.sendKeys(register.getLastName());
        EMAIL_FIELD.sendKeys(register.getEmail());
        PASSWORD_FIELD.sendKeys(register.getPassword());
        RE_PASSWORD_FIELD.sendKeys(register.getPassword());
    }

    @Step("Нажать кнопку создания нового аккаунта Create New Account")
    public void pushRegisterButton() {
        log.info("Нажать кнопку создания нового аккаунта Create New Account");
        CREATE_NEW_ACCOUNT_BUTTON.shouldBe(visible);
        CREATE_NEW_ACCOUNT_BUTTON.click();
    }

    @Step("Проверка валидации пустых полей при создании аккаунта")
    public void verifyEmptyFieldsErrorMessage() {
        log.info("Проверка валидации пустых полей при создании аккаунта");
        EMPTY_FIELD_EMAIL_ERROR_MESSAGE.shouldBe(visible)
                .shouldHave(text("This field is required."));
        EMPTY_FIELD_LAST_NAME_ERROR_MESSAGE.shouldBe(visible)
                .shouldHave(text("This field is required."));
        EMPTY_FIELD_FIRST_NAME_ERROR_MESSAGE.shouldBe(visible)
                .shouldHave(text("This field is required."));
        EMPTY_FIELD_PASSWORD_ERROR_MESSAGE.shouldBe(visible)
                .shouldHave(text("This field is required."));
        EMPTY_FIELD_RE_PASSWORD_ERROR_MESSAGE.shouldBe(visible)
                .shouldHave(text("This field is required."));
    }

    @Step("Проверка валидации невалидного email при создании аккаунта")
    public void verifyInvalidEmailErrorMessage() {
        log.info("Проверка валидации невалидного email при создании аккаунта");
        EMPTY_FIELD_EMAIL_ERROR_MESSAGE.shouldBe(visible)
                .shouldHave(text("Please enter a valid email address."));
    }

    @Step("Проверка валидации слабого пароля при создании аккаунта")
    public void verifyWeakPasswordErrorMessage() {
        log.info("Проверка валидации слабого пароля при создании аккаунта");
        WEAK_PASSWORD_ERROR_MESSAGE.shouldBe(visible)
                .shouldHave(text(" *Please enter a Password value with at least one number, lower-case letter, and upper-case letter between 7 and 15 characters in length."));
    }

    @Step("Нажать на ссылку Already have an account? Login here.")
    public void clickLoginLink() {
        log.info("Нажать на ссылку Already have an account? Login here.");
        ALREADY_HAVE_ACCOUNT_LINK.shouldBe(visible);
        ALREADY_HAVE_ACCOUNT_LINK.click();
    }
}
