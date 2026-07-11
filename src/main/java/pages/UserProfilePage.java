package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Random;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


@Log4j2
public class UserProfilePage extends BasePage{

    private final SelenideElement EDIT_PROFILE_BUTTON  = $("#ProfileEditLink");
    private final SelenideElement CALENDAR_SYNC_BUTTON  =
            $x("//a[contains(@class, 'editlink')]//span[contains(text(), 'Edit Sync')]");
    private final SelenideElement EDIT_SECURITY_BUTTON =
            $x("//a[contains(@class, 'editlink')]//span[contains(text(), 'Edit Security Settings')]");
    private final SelenideElement EDIT_SETTING_BUTTON =
            $x("//a[contains(@class, 'editlink')]//span[contains(text(), 'Edit Settings')]");
    private final SelenideElement AVATAR_IMAGE = $x("//img[@class='img-avatar']");
    private final SelenideElement NAME_FIELD = $x("//p[.//small[text()='Name:']]");
    private final SelenideElement EMAIL_FIELD = $x("//p[.//small[text()='Email:']]");
    private final SelenideElement FIRST_NAME_FIELD  = $("#fname");
    private final SelenideElement LAST_NAME_FIELD  = $("#lname");
    private final SelenideElement SAVE_BUTTON_PROFILE  = $("#saveButtonProfile");
    private final SelenideElement SAVE_BUTTON_CALENDAR_SYNC  = $("#saveButtonSync");
    private final SelenideElement SELECT_TRAINING_MENU = $("#PSport");
    private final SelenideElement PRIMARY_SPORT_FIELD = $x("//p[small[contains(text(),'Primary Sport')]]");
    private final  SelenideElement SAVE_BUTTON_SETTINGS = $("#saveButtonSettings");
    private final  ElementsCollection LANGUAGES = $$x("//label[input[@name='Lang']]");
    private final SelenideElement CHECKED_LANGUAGE =
            $x("//p[i[contains(@class,'flag-')]]");
    private String selectedLanguage;


    public UserProfilePage openPage() {
        log.info("Открыть страницу фидбека");
        Selenide.open("/UserProfile.cshtml");
        return this;
    }

    @Override
    public UserProfilePage isPageOpened() {
        log.info("Проверка, открыта ли страница профиля юзера");
        EDIT_PROFILE_BUTTON.shouldBe(visible);
       CALENDAR_SYNC_BUTTON.shouldBe(visible);
        EDIT_SECURITY_BUTTON.shouldBe(visible);
        EDIT_SETTING_BUTTON.shouldBe(visible);
        return this;
    }

    public UserProfilePage verifyUserProfilePage() {
        log.info("Проверка, есть ли на странице имя, фото пользователя, почта");
        AVATAR_IMAGE.shouldBe(visible);
        NAME_FIELD.shouldBe(visible)
                .shouldNotBe(empty);
        EMAIL_FIELD.shouldBe(visible)
                .shouldNotBe(empty);
        return this;
    }

    public UserProfilePage verifyNameAfterEdit(String firstName, String lastName) {
        log.info("Проверка соответствия имени и фамилии");
        SoftAssert softAssert = new SoftAssert();
        NAME_FIELD.shouldBe(visible)
                .shouldNotBe(empty);
        EMAIL_FIELD.shouldBe(visible)
                .shouldNotBe(empty);
        String expectedFullName = firstName + " " + lastName;
        String actualProfileName = NAME_FIELD.getText();
        softAssert.assertEquals(actualProfileName, expectedFullName,
                "Имя в профиле не соответствует новому значению");
        return this;
    }

    public UserProfilePage clickEditButton() {
        log.info("Нажать на кнопку редактирования профиля");
        EDIT_PROFILE_BUTTON.click();
        return this;
    }

    public UserProfilePage editName(String firstName, String lastName) {
        log.info("Отредактировать имя пользователя на новое: {}", firstName);
        FIRST_NAME_FIELD.shouldBe(visible).setValue(firstName);
        LAST_NAME_FIELD.shouldBe(visible).setValue(lastName);
        SAVE_BUTTON_PROFILE.shouldBe(visible).click();
    return this;
    }

    public UserProfilePage clickEditSettingsButton() {
        log.info("Нажать на кнопку редактирования настроек");
        EDIT_SETTING_BUTTON.click();
        return this;
    }

    public String selectRandomSport() {
            log.info("Отредактировать настройки профиля");
            List<String> sports = SELECT_TRAINING_MENU.$$("option")
                    .texts();
            sports.remove(0);
            String randomSport = sports.get(new Random().nextInt(sports.size()));
            SELECT_TRAINING_MENU.selectOption(randomSport);
            return randomSport;
        }

        public  UserProfilePage saveSettings() {
            log.info("Нажать на кнопку сохранения настроек");
        SAVE_BUTTON_SETTINGS.shouldBe(visible).click();
        return this;
        }

    public  UserProfilePage verifySelectedSport(String expectedSport) {
        log.info("Проверка выбранного спорта");
        PRIMARY_SPORT_FIELD.shouldHave(text(expectedSport));
        return this;
    }

    public UserProfilePage selectRandomLanguage() {
        log.info("Выбор случайного языка в настройках");
        int randomIndex = new Random().nextInt(LANGUAGES.size());
        SelenideElement language = LANGUAGES.get(randomIndex);
        selectedLanguage = language.getText().trim();
        language.click();
        return this;
    }

    public UserProfilePage verifySelectedLanguage() {
        log.info("Проверка выбранного языка");
        CHECKED_LANGUAGE.shouldHave(text(selectedLanguage));
        return this;
    }

    public UserProfilePage clickEditCalendarSyncButton() {
        log.info("Нажать на кнопку редактирования cинхронизации календаря");
        CALENDAR_SYNC_BUTTON.click();
        return this;
    }

    public UserProfilePage clickSaveCalendarSyncButton() {
        log.info("Нажать на кнопку редактирования cинхронизации календаря");
        SAVE_BUTTON_CALENDAR_SYNC.click();
        return this;
    }

    public UserProfilePage changeCalendarSync() {
        log.info("Нажать на радиобаттон синхронизации");
        $("#SyncOff").shouldBe(selected);
        $("#SyncOn").click();
        $("#SyncOn").shouldBe(selected);
        return this;
    }
}
