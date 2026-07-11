package tests;

import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.title;
import static constants.ConstantElements.email;
import static constants.ConstantElements.password;
import static elements.Elements.*;

public class UserProfileTest extends BaseTest{

    private static final Faker faker = new Faker();

    @DataProvider(name = "validUserData")
    public Object[][] validUserData() {
        return new Object[][] {
                {
                        faker.name().firstName(),
                        faker.name().lastName(),
                }
        };
    }

    @Test(testName = "Просмотр профиля "
            ,  description = "Отображаются: имя, email, фото профиля")
    public void checkUserPrifilePage() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        userProfilePage.openPage()
                        .isPageOpened()
                                .verifyUserProfilePage();
        softAssert.assertEquals(title(), titleForUserProfilePage);
        verifyUrl(urlUserProfile);
        isTextDisplayed("User Profile");
        softAssert.assertAll();
    }

    @Test(testName = "Редактирование имени и фамилии"
            , dataProvider = "validUserData"
            , description = "После сохранения новое имя отображается в профиле и шапке сайта")
    public void checkEditNameSurname(String firstName, String lastName) {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        userProfilePage.openPage()
                .isPageOpened()
                .clickEditButton()
                .editName(firstName, lastName)
                .verifyNameAfterEdit(firstName, lastName);
        softAssert.assertAll();
    }

    @Test(testName = "Редактирование User Settings тип тренировки"
            ,  description = "Выбор типа тренировки")
    public void checkSettingsTrainningChange() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        userProfilePage.openPage()
                       .isPageOpened()
                .clickEditSettingsButton();
        String expectedSport = userProfilePage.selectRandomSport();
        userProfilePage.saveSettings()
                .verifySelectedSport(expectedSport);
        softAssert.assertAll();
    }

    @Test(testName = "Редактирование User Settings язык"
            ,  description = "Выбор языка типа тренировки")
    public void checkSettingsLanguageChange() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        userProfilePage.openPage()
                .isPageOpened()
                .clickEditSettingsButton()
                .selectRandomLanguage()
                .saveSettings()
                .verifySelectedLanguage();
        softAssert.assertAll();
    }

    @Test(testName = "Редактирование Calendar Sync"
            ,  description = "Чекбоксы on/off - сохранить изменения ")
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
        isTextDisplayed(MESSAGE_CALENDAR_SYNC_SUCCESFULL);
        softAssert.assertAll();
    }

    @Test(testName = "Загрузка аватара"
            ,  description = "После выбора файла изображение отображается в профиле")
    public void checkAvatarUploaded() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        userProfilePage.openPage()
                .isPageOpened()
                .clickEditButton()
                .uploadProfileImage("images/cat.jpg")
                .saveSettings();
        softAssert.assertAll();
    }

    @Test(testName = "Настройки уведомлений (Notifications)"
            ,  description = "Чекбоксы вкл/выкл сохраняются, настройки применяются ")
    public void checkNotification() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        userProfilePage.openPage()
                .isPageOpened()
                .clickEditSettingsButton()
                .changeEmailNotification()
                .saveSettings();
        softAssert.assertAll();
    }

    @Test(testName = "Настройки приватности"
            ,  description = "Настройки видимости профиля сохраняются")
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
