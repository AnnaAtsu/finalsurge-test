package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Random;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static urls.Urls.userProfileEndpoint;


@Log4j2
public class UserProfilePage extends BasePage {

    private final SelenideElement EDIT_PROFILE_BUTTON = $("#ProfileEditLink");
    private final SelenideElement CALENDAR_SYNC_BUTTON =
            $x("//a[contains(@class, 'editlink')]//span[contains(text(), 'Edit Sync')]");
    private final SelenideElement EDIT_SECURITY_BUTTON =
            $x("//a[contains(@class, 'editlink')]//span[contains(text(), 'Edit Security Settings')]");
    private final SelenideElement EDIT_SETTING_BUTTON =
            $x("//a[contains(@class, 'editlink') and contains(@href, '#settings')]");
    private final SelenideElement AVATAR_IMAGE = $x("//img[@class='img-avatar']");
    private final SelenideElement NAME_FIELD = $x("//p[.//small[text()='Name:']]");
    private final SelenideElement EMAIL_FIELD = $x("//p[.//small[text()='Email:']]");
    private final SelenideElement FIRST_NAME_FIELD = $("#fname");
    private final SelenideElement LAST_NAME_FIELD = $("#lname");
    private final SelenideElement SAVE_BUTTON_PROFILE = $("#saveButtonProfile");
    private final SelenideElement SAVE_BUTTON_CALENDAR_SYNC = $("#saveButtonSync");
    private final SelenideElement SELECT_TRAINING_MENU = $("#PSport");
    private final SelenideElement PRIMARY_SPORT_FIELD = $x("//p[small[contains(text(),'Primary Sport')]]");
    private final SelenideElement SAVE_BUTTON_SETTINGS = $("#saveButtonSettings");
    private final ElementsCollection LANGUAGES = $$x("//label[input[@name='Lang']]");
    private final SelenideElement CHECKED_LANGUAGE =
            $x("//p[i[contains(@class,'flag-')]]");
    private String selectedLanguage;
    private String initialUpdatesValue;
    private final SelenideElement PUSH_IMAGE_BUTTON = $("#EditProfilePicOther");
    private final SelenideElement SELECT_IMAGE_BUTTON = $x("//input[@type='file' and @name='profilepic']");
    private final SelenideElement UPLOAD_BUTTON = $("#NextStep");
    private final SelenideElement UPLOADER_FRAME = $("#uploader");
    private final SelenideElement SAVE_NEW_IMAGE_BUTTON = $x("//a[text()='Save']");


    @Step("Открыть страницу фидбека")
    public UserProfilePage openPage() {
        log.info("Открыть страницу фидбека");
        Selenide.open(userProfileEndpoint);
        return this;
    }

    @Override
    @Step("Проверка, открыта ли страница профиля юзера")
    public UserProfilePage isPageOpened() {
        log.info("Проверка, открыта ли страница профиля юзера");
        EDIT_PROFILE_BUTTON.shouldBe(visible);
        CALENDAR_SYNC_BUTTON.shouldBe(visible);
        EDIT_SECURITY_BUTTON.shouldBe(visible);
        EDIT_SETTING_BUTTON.shouldBe(visible);
        return this;
    }

    @Step("Проверка, есть ли на странице имя, фото пользователя, почта")
    public UserProfilePage verifyUserProfilePage() {
        log.info("Проверка, есть ли на странице имя, фото пользователя, почта");
        AVATAR_IMAGE.shouldBe(visible);
        NAME_FIELD.shouldBe(visible)
                .shouldNotBe(empty);
        EMAIL_FIELD.shouldBe(visible)
                .shouldNotBe(empty);
        return this;
    }

    @Step("Проверка соответствия имени и фамилии")
    public UserProfilePage verifyNameAfterEdit(String firstName, String lastName) {
        log.info("Проверка соответствия имени и фамилии");
        NAME_FIELD.shouldBe(visible)
                .shouldNotBe(empty);
        EMAIL_FIELD.shouldBe(visible)
                .shouldNotBe(empty);
        String expectedFullName = firstName + " " + lastName;
        NAME_FIELD.shouldHave(text(expectedFullName));
        return this;
    }

    @Step("Нажать на кнопку редактирования профиля")
    public UserProfilePage clickEditButton() {
        log.info("Нажать на кнопку редактирования профиля");
        EDIT_PROFILE_BUTTON.click();
        return this;
    }

    @Step("Отредактировать имя пользователя на новое: {}")
    public UserProfilePage editName(String firstName, String lastName) {
        log.info("Отредактировать имя пользователя на новое: {}", firstName);
        FIRST_NAME_FIELD.shouldBe(visible).setValue(firstName);
        LAST_NAME_FIELD.shouldBe(visible).setValue(lastName);
        SAVE_BUTTON_PROFILE.shouldBe(visible).click();
        return this;
    }

    @Step("Нажать на кнопку редактирования настроек")
    public UserProfilePage clickEditSettingsButton() {
        log.info("Нажать на кнопку редактирования настроек");
        EDIT_SETTING_BUTTON.click();
        return this;
    }

    @Step("Отредактировать настройки профиля")
    public String selectRandomSport() {
        log.info("Отредактировать настройки профиля");
        List<String> sports = SELECT_TRAINING_MENU.$$("option")
                .texts();
        sports.remove(0);
        String randomSport = sports.get(new Random().nextInt(sports.size()));
        SELECT_TRAINING_MENU.selectOption(randomSport);
        return randomSport;
    }

    @Step("Нажать на кнопку сохранения настроек")
    public UserProfilePage saveSettings() {
        log.info("Нажать на кнопку сохранения настроек");
        SAVE_BUTTON_SETTINGS.shouldBe(visible).click();
        return this;
    }

    @Step("Проверка выбранного спорта")
    public UserProfilePage verifySelectedSport(String expectedSport) {
        log.info("Проверка выбранного спорта");
        PRIMARY_SPORT_FIELD.shouldHave(text(expectedSport));
        return this;
    }

    @Step("Выбор случайного языка в настройках")
    public UserProfilePage selectRandomLanguage() {
        log.info("Выбор случайного языка в настройках");
        int randomIndex = new Random().nextInt(LANGUAGES.size());
        SelenideElement language = LANGUAGES.get(randomIndex);
        selectedLanguage = language.getText().trim();
        language.click();
        return this;
    }

    @Step("Проверка выбранного языка")
    public UserProfilePage verifySelectedLanguage() {
        log.info("Проверка выбранного языка");
        CHECKED_LANGUAGE.shouldHave(text(selectedLanguage));
        return this;
    }

    @Step("Сбросить язык на английский")
    public UserProfilePage selectLanguageEnglish() {
        log.info("Сбросить язык на английский");
        LANGUAGES.findBy(text("English")).click();
        SAVE_BUTTON_SETTINGS.shouldBe(visible).click();
        return this;
    }

    @Step("Нажать на кнопку редактирования cинхронизации календаря")
    public UserProfilePage clickEditCalendarSyncButton() {
        log.info("Нажать на кнопку редактирования cинхронизации календаря");
        CALENDAR_SYNC_BUTTON.click();
        return this;
    }

    @Step("Нажать на кнопку редактирования cинхронизации календаря")
    public UserProfilePage clickSaveCalendarSyncButton() {
        log.info("Нажать на кнопку редактирования cинхронизации календаря");
        SAVE_BUTTON_CALENDAR_SYNC.click();
        return this;
    }

    @Step("Нажать на радиобаттон синхронизации")
    public UserProfilePage changeCalendarSync() {
        log.info("Нажать на радиобаттон синхронизации");
        SelenideElement selectedRadio = $$("input[name='CalSync']")
                .findBy(selected);
        initialUpdatesValue = selectedRadio.getAttribute("id");
        if ("SyncOff".equals(initialUpdatesValue)) {
            $("#SyncOn").parent().click();
            $("#SyncOn").shouldBe(selected);
        } else {
            $("#SyncOff").parent().click();
            $("#SyncOff").shouldBe(selected);
        }
        return this;
    }

    @Step("Загрузка изображения профиля")
    public UserProfilePage uploadProfileImage(String fileName) {
        log.info("Загрузка изображения профиля");
        PUSH_IMAGE_BUTTON.click();
        switchTo().frame(UPLOADER_FRAME);
        SELECT_IMAGE_BUTTON.uploadFromClasspath(fileName);
        switchTo().defaultContent();
        UPLOAD_BUTTON.shouldBe(visible).click();
        SAVE_NEW_IMAGE_BUTTON.shouldBe(visible).click();
        return this;
    }

    @Step("Нажать на кнопку сохранения изображения")
    public UserProfilePage saveProfileImage() {
        log.info("Загрузка изображения профиля");
        UPLOAD_BUTTON.shouldBe(visible).click();
        return this;
    }

    @Step("Нажать на радиобаттон почтовых уведомлений")
    public UserProfilePage changeEmailNotification() {
        log.info("Нажать на радиобаттон почтовых уведомлений");
        SelenideElement selectedRadio = $$("input[name='EmailWorkouts']")
                .findBy(selected);
        initialUpdatesValue = selectedRadio.getAttribute("id");
        if ("EW0".equals(initialUpdatesValue)) {
            $("#EW1").parent().click();
            $("#EW1").shouldBe(selected);
        } else {
            $("#EW0").parent().click();
            $("#EW0").shouldBe(selected);
        }
        return this;
    }

    @Step("Нажать на радиобаттон почтовых уведомлений")
    public UserProfilePage changeUpdates() {
        log.info("Нажать на радиобаттон почтовых уведомлений");
        SelenideElement selectedRadio = $$("input[name='EmailUpdates']")
                .findBy(selected);
        initialUpdatesValue = selectedRadio.getAttribute("id");
        if ("EU1".equals(initialUpdatesValue)) {
            $("#EU2").parent().click();
            $("#EU2").shouldBe(selected);
        } else {
            $("#EU1").parent().click();
            $("#EU1").shouldBe(selected);
        }
        return this;
    }
}
