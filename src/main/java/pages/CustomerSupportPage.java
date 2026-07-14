package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class CustomerSupportPage extends BasePage{

    private final SelenideElement PHONE_FIELD  = $("#Phone");
    private final SelenideElement REASON_FIELD  = $("#Reason");
    private final SelenideElement FEEDBACK_FIELD  = $("#Feedback");
    private final SelenideElement FEEDBACK_SEND_BUTTON  = $("#submitButton");


    @Step("Открыть страницу поддержки")
    public CustomerSupportPage openPage() {
        log.info("Открыть страницу поддержки");
        Selenide.open("/CustSupport.cshtml");
        return this;
    }

    @Override
    @Step("Проверка, открыта ли страница саппорта")
    public CustomerSupportPage isPageOpened() {
        log.info("Проверка, открыта ли страница саппорта");
        PHONE_FIELD.should(visible);
        REASON_FIELD.shouldBe(visible);
        FEEDBACK_FIELD.shouldBe(visible);
        FEEDBACK_SEND_BUTTON.shouldBe(visible);
        return this;
    }
}
