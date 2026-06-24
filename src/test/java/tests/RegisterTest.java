package tests;

import dto.Register;
import dto.RegisterFactory;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import static com.codeborne.selenide.Selenide.title;
import static elements.Elements.titleForCreateNewAccount;
import static elements.Elements.titleForDashboardPage;

@Log4j2
public class RegisterTest extends BaseTest {

    Register registerTestSuccessfullRegistration = RegisterFactory.getRegister("Texts56456!");
    Register registerTestforWeakPassword = RegisterFactory.getRegister("Te");
    Register registerTestForEmailValidation = new Register("John", "Doe", "jjfjg@", "Texts56456!");
    @Test(description = "Успешная регистрация в системе")
    public void checkSuccessfullRegistration() {
        SoftAssert softAssert = new SoftAssert();
        registerPage.openPage()
                .isPageOpened();
        registerPage.createAccount(registerTestSuccessfullRegistration);
        registerPage.pushRegisterButton();
        softAssert.assertEquals(title(), titleForDashboardPage);
        softAssert.assertAll();
    }

    @Test(description = "Проверка валидации некорректного email")
    public void checkValidMessageForEmailForRegistration() {
        SoftAssert softAssert = new SoftAssert();
        registerPage.openPage()
                     .isPageOpened();
        registerPage.createAccount(registerTestForEmailValidation);
        registerPage.pushRegisterButton();
        registerPage.verifyInvalidEmailErrorMessage();
        softAssert.assertEquals(title(), titleForCreateNewAccount);
        softAssert.assertAll();
    }

    @Test(description = "Проверка валидации пустых полей")
    public void checkValidMessagesForEmptyRegistration() {
        SoftAssert softAssert = new SoftAssert();
        registerPage.openPage()
                .isPageOpened();
        registerPage.pushRegisterButton();
        registerPage.verifyEmptyFieldsErrorMessage();
        softAssert.assertEquals(title(), titleForCreateNewAccount);
        softAssert.assertAll();
    }

    @Test(description = "Проверка валидации слабого пароля")
    public void checkValidMessageForWeakPassword() {
        SoftAssert softAssert = new SoftAssert();
        registerPage.openPage()
                .isPageOpened();
        registerPage.createAccount(registerTestforWeakPassword);
        registerPage.pushRegisterButton();
        registerPage.verifyWeakPasswordErrorMessage();
        softAssert.assertEquals(title(), titleForCreateNewAccount);
        softAssert.assertAll();
    }
}
