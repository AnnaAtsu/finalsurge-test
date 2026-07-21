package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static urls.Urls.calendarEndpoint;


@Log4j2
public class DashboardPage extends BasePage {


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
    private final SelenideElement SIX_WEEKS_BUTTON = $x("//span[text()='6 weeks']");
    private final SelenideElement SIX_WEEK_FIELD = $x("//ul[@class='dropdown-menu']//a[text()='6 weeks (forward)']");

    private final SelenideElement WORKOUT_MENU = $x("//a[text()='Workouts']");
    private final ElementsCollection WORKOUT_MENU_ITEMS = $$x("//a[text()='Workouts']/following-sibling::ul//a");

    private final SelenideElement DAILY_VITALS_MENU = $x("//a[text()='Daily Vitals']");
    private final ElementsCollection DAILY_VITALS_ITEMS = $$x("//a[text()='Daily Vitals']/following-sibling::ul//a");

    private final SelenideElement GEAR_ROUTES_MENU = $x("//a[text()='Gear & Routes']");
    private final ElementsCollection GEAR_ROUTES_ITEMS = $$x("//a[text()='Gear & Routes']/following-sibling::ul//a");
    private final SelenideElement TRAINING_PLAN_MENU = $x("//a[text()='Training Plans']");
    private final ElementsCollection TRAINING_PLAN_ITEMS = $$x("//a[text()='Training Plans']/following-sibling::ul//a");
    private final SelenideElement RESOURCES_MENU = $x("//a[text()='Resources']");
    private final ElementsCollection RESOURCES_ITEMS = $$x("//a[text()='Resources']/following-sibling::ul//a");
    private final SelenideElement MESSAGE_MENU = $x("//a[text()='Message Boards']");
    private final SelenideElement MAIN_LOGO = $x("//div[@class='main-logo']/a");

    @Step("Открыть страницу дашборда")
    public DashboardPage openPage() {
        log.info("Открыть страницу дашборда");
        Selenide.open(calendarEndpoint);
        return this;
    }

    @Override
    @Step("Проверка, открыта ли страница дашборда")
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

    @Step("Вернуться на страницу дашборда")
    public void clickCalendarPage() {
        log.info("Вернуться на страницу дашборда");
        CALENDAR_LINK.click();
    }

    @Step("Открыть страницу дефолтную")
    public void clickDefaultPage() {
        log.info("Открыть страницу дефолтную");
        HOME_LINK.click();
    }

    @Step("Открыть страницу отчетов")
    public void clickReportPage() {
        log.info("Открыть страницу отчетов");
        REPORTS_LINK.click();
    }

    @Step("Открыть страницу сообщения")
    public void clickMailPage() {
        log.info("Открыть страницу сообщения");
        MAIL_LINK.click();
    }

    @Step("Нажать на кнопку отображения недели")
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

    @Step("Нажать на кнопку Garmin")
    public DashboardPage clickGarminButton() {
        log.info("Нажать на кнопку Garmin");
        GARMIN_BUTTON.should(visible);
        GARMIN_BUTTON.click();
        return this;
    }

    @Step("Нажать на кнопку 6 weeks и выбрать в выпадающем списке 6 weeks")
    public DashboardPage clickSixWeeksButton() {
        log.info("Нажать на кнопку 6 weeks и выбрать в выпадающем списке 6 weeks");
        SIX_WEEKS_BUTTON.should(visible);
        SIX_WEEKS_BUTTON.click();
        SIX_WEEK_FIELD.shouldBe(visible);
        SIX_WEEK_FIELD.click();
        return this;
    }

    @Step("Подсчет количества строк для 6 недель")
    public int verifyWeekCount() {
        log.info("Подсчет количества строк для 6 недель");
        $$("td[data-weeknumber]").first().shouldBe(visible);
        return (int) $$("td[data-weeknumber]")
                .stream()
                .map(td -> td.getAttribute("data-weeknumber"))
                .distinct()
                .count();
    }

    private final List<String> EXPECTED_ITEMS = Arrays.asList(
            "Add Workout", "Garmin / Device Upload", "View Calendar",
            "Reports & Statistics", "Print Workouts", "Workout Library",
            "HR/Power/Pace Zones", "Customize Activity Types", "Import Data"
    );

    private final List<String> GEAR_ROUTES_EXPECTED_ITEMS = Arrays.asList("Shoes", "Bikes", "Routes");
    private final List<String> TRAINING_PLAN_EXPECTED_ITEMS = Arrays.asList("View My Plans", "Find a Plan");


    @Step("Отображение верхнего кликабельного меню над логотипом")
    public void verifyDropdownMenuShouldBeVisibleAfterClick() {
        log.info("Отображение верхнего кликабельного меню над логотипом");
        WORKOUT_MENU.shouldBe(visible).click();
        WORKOUT_MENU_ITEMS.shouldHave(texts(EXPECTED_ITEMS));
        DAILY_VITALS_MENU.shouldBe(visible).click();
        DAILY_VITALS_ITEMS.shouldHave(texts("View & Add Vitals"));
        GEAR_ROUTES_MENU.shouldBe(visible).click();
        GEAR_ROUTES_ITEMS.shouldHave(texts(GEAR_ROUTES_EXPECTED_ITEMS));
        TRAINING_PLAN_MENU.shouldBe(visible).click();
        TRAINING_PLAN_ITEMS.shouldHave(texts(TRAINING_PLAN_EXPECTED_ITEMS));
        RESOURCES_MENU.shouldBe(visible).click();
        RESOURCES_ITEMS.shouldHave(texts("View Files & Resources"));
    }

    @Step("Нажать на кнопку Message Board")
    public void clickMessageBoardButton() {
        log.info("Нажать на кнопку Message Board");
        MESSAGE_MENU.should(visible).click();
    }

    @Step("Нажать на иконку логотипа")
    public void clickMainLogo() {
        log.info("Нажать на иконку логотипа");
        MAIN_LOGO.should(visible).click();
    }
}
