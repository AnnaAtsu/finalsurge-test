package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import dto.Feedback;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static urls.Urls.feedbackEndpoint;

@Log4j2
public class FeedbackPage extends BasePage {

    private final SelenideElement PHONE_FIELD = $("#Phone");
    private final SelenideElement REASON_FIELD = $("#Reason");
    private final SelenideElement FEEDBACK_FIELD = $("#Feedback");
    private final SelenideElement FEEDBACK_SEND_BUTTON = $("#submitButton");

    @Step("Открыть страницу фидбека")
    public FeedbackPage openPage() {
        log.info("Открыть страницу фидбека");
        Selenide.open(feedbackEndpoint);
        return this;
    }

    @Override
    @Step("Проверка, открыта ли страница фидбека")
    public FeedbackPage isPageOpened() {
        log.info("Проверка, открыта ли страница фидбека");
        PHONE_FIELD.should(visible);
        REASON_FIELD.shouldBe(visible);
        FEEDBACK_FIELD.shouldBe(visible);
        FEEDBACK_SEND_BUTTON.shouldBe(visible);
        return this;
    }


    @Step("Создание аккаунта '{}'")
    public FeedbackPage fillFeedback(Feedback feedback) {
        log.info("Создание аккаунта '{}'", feedback.getPhoneNumber());
        PHONE_FIELD.sendKeys(feedback.getPhoneNumber());
        FEEDBACK_FIELD.sendKeys(feedback.getDescription());
        return this;
    }


    @Step("Нажать на кнопку Save Feedback")
    public FeedbackPage pushFeedbackButton() {
        log.info("Нажать на кнопку Save Feedback");
        FEEDBACK_SEND_BUTTON.click();
        return this;
    }
}
