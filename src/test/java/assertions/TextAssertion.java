package assertions;

import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
@Log4j2
public class TextAssertion {

    public boolean isTextDisplayed(String text) {
        log.info("Проверка отображения текста " + text);
        return $x("//*[contains(text(), '" + text + "')]").isDisplayed();
    }

    public void isTextNotDisplayed(String text) {
        log.info("Проверка, что текст отсутствует: " + text);
        $x("//*[contains(text(), '" + text + "')]").shouldNotBe(visible);
    }
}
