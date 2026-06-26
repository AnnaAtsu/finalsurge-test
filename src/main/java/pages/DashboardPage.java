package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static elements.Elements.CANT_SIGN_IN_FORM_TITLE;


@Log4j2
public class DashboardPage extends BasePage{


    private final SelenideElement HOME_LINK = $x("//a[@class='ptip_s' and @href='Default.cshtml']");
    private final SelenideElement CALENDAR_LINK = $x("//a[@class='ptip_s' and @href='Calendar.cshtml']");
    private final SelenideElement SETTINGS_LINK = $("a[href$='UserProfile.cshtml']");
    private final SelenideElement REPORTS_LINK = $x("//a[@class='ptip_s' and @href='WorkoutReport.cshtml']");
    private final SelenideElement CALENDAR_CONTENT_BLOCK = $("#CalendarContent");
    private final SelenideElement USER_BLOCK = $(".user-box-inner");
    private final SelenideElement NAV_ICONS = $(".nav-icons");
    private final SelenideElement UPPER_MENU = $("#fade-menu");
    private final SelenideElement MAIL_LINK = $x("//a[@class='ptip_s' and @href='Mailbox.cshtml']");
    private final SelenideElement WEEK_BUTTON = $x("//span[text()='week']");
    private final SelenideElement WEEK_HEADER_TITLE = $x("//span/h2/a[contains(text(), 'Week')]");
    private final SelenideElement GARMIN_BUTTON = $("#GarminAddBtn");



    public DashboardPage openPage() {
        log.info("Открыть страницу дашборда");
        Selenide.open("/Calendar.cshtml");
        return this;
    }

    @Override
    public DashboardPage isPageOpened() {
        log.info("Проверка, открыта ли страница дашборда");
        CALENDAR_CONTENT_BLOCK.should(visible);
        NAV_ICONS.should(visible);
        USER_BLOCK.should(visible);
        UPPER_MENU.should(visible);
        REPORTS_LINK.should(visible);
        HOME_LINK.should(visible);
        CALENDAR_LINK.should(visible);
        SETTINGS_LINK.should(visible);
        return this;
    }

    public void clickCalendarPage() {
        log.info("Вернуться на страницу дашборда");
        CALENDAR_LINK.click();
    }

    public void clickDefaultPage() {
        log.info("Открыть страницу дефолтную");
        HOME_LINK.click();
    }

    public void clickReportPage() {
        log.info("Открыть страницу отчетов");
        REPORTS_LINK.click();
    }

    public void clickMailPage() {
        log.info("Открыть страницу сообщения");
        MAIL_LINK.click();
    }

    public DashboardPage clickWeekButton() {
        log.info("Нажать на кнопку отображения недели");
        WEEK_BUTTON.click();
        return this;
    }

    public DashboardPage verifyWeekHeader() {
        WEEK_HEADER_TITLE.should(visible);
        WEEK_HEADER_TITLE.shouldHave(text("Week"));
        return this;
    }

    public DashboardPage clickGarminButton() {
        log.info("Нажать на кнопку Garmin");
        GARMIN_BUTTON.should(visible);
        GARMIN_BUTTON.click();
        return this;
    }
}
