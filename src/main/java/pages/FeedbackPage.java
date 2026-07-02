package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import dto.FeedBackFactory;
import dto.Feedback;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class FeedbackPage extends BasePage{

    private final SelenideElement PHONE_FIELD  = $("#Phone");
    private final SelenideElement REASON_FIELD  = $("#Reason");
    private final SelenideElement FEEDBACK_FIELD  = $("#Feedback");
    private final SelenideElement FEEDBACK_SEND_BUTTON  = $("#submitButton");

    public FeedbackPage openPage() {
        log.info("Открыть страницу фидбека");
        Selenide.open("/Feedback.cshtml");
        return this;
    }

    @Override
    public FeedbackPage isPageOpened() {
        log.info("Проверка, открыта ли страница фидбека");
        PHONE_FIELD.should(visible);
        REASON_FIELD.shouldBe(visible);
        FEEDBACK_FIELD.shouldBe(visible);
        FEEDBACK_SEND_BUTTON.shouldBe(visible);
        return this;
    }


    public FeedbackPage fillFeedback(Feedback feedback) {
        log.info("Создание аккаунта '{}'", feedback.getPhoneNumber());
        PHONE_FIELD.sendKeys(feedback.getPhoneNumber());
        FEEDBACK_FIELD.sendKeys(feedback.getDescription());
        return this;
    }


    public FeedbackPage pushFeedbackButton() {
        log.info("Нажать на кнопку Save Feedback");
        FEEDBACK_SEND_BUTTON.click();
        return this;
    }
}
