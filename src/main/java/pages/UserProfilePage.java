package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;


@Log4j2
public class UserProfilePage extends BasePage{

    private final SelenideElement EDIT_PROFILE_BUTTON  = $("#ProfileEditLink");
    private final SelenideElement CALENDAR_SYNC_BUTTON  = $x("//a[contains(@class, 'editlink')]//span[contains(text(), 'Edit Sync')]");
    private final SelenideElement EDIT_SECURITY_BUTTON =   $x("//a[contains(@class, 'editlink')]//span[contains(text(), 'Edit Security Settings')]");
    private final SelenideElement EDIT_SETTING_BUTTON = $x("//a[contains(@class, 'editlink')]//span[contains(text(), 'Edit Settings')]");
    private final SelenideElement AVATAR_IMAGE = $x("//img[@class='img-avatar']");
    private final SelenideElement NAME_FIELD = $x("//p[.//small[text()='Name:']]");
    private final SelenideElement EMAIL_FIELD = $x("//p[.//small[text()='Email:']]");
    private final SelenideElement FIRST_NAME_FIELD  = $("#fname");
    private final SelenideElement LAST_NAME_FIELD  = $("#lname");
    private final SelenideElement SAVE_BUTTON_PROFILE  = $("#saveButtonProfile");

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

    public UserProfilePage clickEditButton() {
        log.info("Нажать на кнопку редактирования профиля");
        EDIT_PROFILE_BUTTON.click();
        return this;
    }

    public UserProfilePage editName(String firstName, String lastName) {
        log.info("Отредактировать имя и фамилию");
        FIRST_NAME_FIELD.shouldBe(visible).setValue(firstName);
        LAST_NAME_FIELD.shouldBe(visible).setValue(lastName);
        SAVE_BUTTON_PROFILE.shouldBe(visible).click();
    return this;
    }
}
