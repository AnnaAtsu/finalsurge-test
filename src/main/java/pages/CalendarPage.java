package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class CalendarPage extends BasePage{
    private final SelenideElement GIVE_FEEDBACK_LINK = $x("//a[text()='Give Feedback']");
    private final SelenideElement CALENDAR_CONTENT_BLOCK = $("#CalendarContent");
    private final SelenideElement TRAINING_CALENDAR_LINK = $x("//a[text()='Training Calendar']");
    public CalendarPage ckickGiveFeedBackLink() {
        GIVE_FEEDBACK_LINK.shouldBe(visible).click();
        return this;
    }

    @Override
    public CalendarPage isPageOpened() {
        log.info("Проверка, открыта ли страница дашборда");
        CALENDAR_CONTENT_BLOCK.should(visible);
        TRAINING_CALENDAR_LINK.shouldBe(visible);
        return this;
    }

}
