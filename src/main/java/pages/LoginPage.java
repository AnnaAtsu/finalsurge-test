package pages;

import com.codeborne.selenide.Selenide;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class LoginPage {

    public LoginPage openPage() {
        log.info("Открытие страницы авторизации");
        Selenide.open("/login.cshtml");
        return this;
    }
}
