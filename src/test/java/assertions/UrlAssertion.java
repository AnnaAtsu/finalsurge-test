package assertions;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class UrlAssertion {

    public void verifyUrl(String expectedUrl) {
        webdriver().shouldHave(url(expectedUrl));
    }
}
