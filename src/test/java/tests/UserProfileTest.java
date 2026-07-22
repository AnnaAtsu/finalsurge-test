package tests;

import com.github.javafaker.Faker;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.logging.log4j.core.config.Order;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Selenide.title;
import static constants.ConstantElements.email;
import static constants.ConstantElements.password;
import static elements.Elements.*;
import static urls.Urls.urlUserProfile;

public class UserProfileTest extends BaseTest {

    private static final Faker faker = new Faker();

    @DataProvider(name = "validUserData")
    public Object[][] validUserData() {
        return new Object[][]{
                {
                        faker.name().firstName(),
                        faker.name().lastName(),
                }
        };
    }

    @Test(testName = "Просмотр профиля "
            , description = "Отображаются: имя, email, фото профиля")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("UserProfile component")
    public void checkUserPrifilePage() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        userProfilePage.openPage()
                .isPageOpened()
                .verifyUserProfilePage();
        softAssert.assertEquals(title(), titleForUserProfilePage);
        urlAssertion.verifyUrl(urlUserProfile);
        softAssert.assertTrue(textAssertion.isTextDisplayed(USER_PROFILE_TITLE));
        softAssert.assertAll();
    }

    @Test(testName = "Редактирование имени и фамилии"
            , dataProvider = "validUserData"
            , description = "После сохранения новое имя отображается в профиле и шапке сайта")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("UserProfile component")
    public void checkEditNameSurname(String firstName, String lastName) {
        loginPage.openPage();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        userProfilePage.openPage()
                .isPageOpened()
                .clickEditButton()
                .editName(firstName, lastName)
                .verifyNameAfterEdit(firstName, lastName);
    }

    @Test(testName = "Редактирование User Settings тип тренировки"
            , description = "Выбор типа тренировки")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("UserProfile component")
    public void checkSettingsTrainningChange() {
        loginPage.openPage();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        userProfilePage.openPage()
                .isPageOpened()
                .clickEditSettingsButton();
        String expectedSport = userProfilePage.selectRandomSport();
        userProfilePage.saveSettings()
                .verifySelectedSport(expectedSport);
    }

    @Test(testName = "Редактирование User Settings язык"
            , description = "Выбор языка типа тренировки")
    @Severity(SeverityLevel.NORMAL)
    @Feature("UserProfile component")
    public void checkSettingsLanguageChange() {
        loginPage.openPage();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        userProfilePage.openPage()
                .isPageOpened()
                .clickEditSettingsButton()
                .selectRandomLanguage()
                .saveSettings()
                .verifySelectedLanguage()
                .clickEditSettingsButton()
                .selectLanguageEnglish();
    }

    @Test(testName = "Редактирование Calendar Sync"
            , description = "Чекбоксы on/off - сохранить изменения ")
    @Severity(SeverityLevel.MINOR)
    @Feature("UserProfile component")
    public void checkCalendarSyncChange() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        userProfilePage.openPage()
                .isPageOpened()
                .clickEditCalendarSyncButton()
                .changeCalendarSync()
                .clickSaveCalendarSyncButton();
        softAssert.assertTrue(textAssertion.isTextDisplayed(MESSAGE_CALENDAR_SYNC_SUCCESFULL));
        softAssert.assertAll();
    }

    @Test(testName = "Загрузка аватара"
            , description = "После выбора файла изображение отображается в профиле")
    @Severity(SeverityLevel.NORMAL)
    @Feature("UserProfile component")
    public void checkAvatarUploaded() {
        loginPage.openPage();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        userProfilePage.openPage()
                .isPageOpened()
                .clickEditButton()
                .uploadProfileImage("images/cat.jpg")
                .saveProfileImage();
    }

    @Test(testName = "Настройки уведомлений (Notifications)"
            , description = "Чекбоксы вкл/выкл сохраняются, настройки применяются ")
    @Severity(SeverityLevel.MINOR)
    @Feature("UserProfile component")
    public void checkNotification() {
        loginPage.openPage();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        userProfilePage.openPage()
                .isPageOpened()
                .clickEditSettingsButton()
                .changeEmailNotification()
                .saveSettings();
    }

    @Test(testName = "Настройки приватности"
            , description = "Настройки видимости профиля сохраняются")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("UserProfile component")
    public void checkPrivacy() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        userProfilePage.openPage()
                .isPageOpened()
                .clickEditSettingsButton()
                .changeUpdates()
                .saveSettings();
        softAssert.assertAll();
    }
}
