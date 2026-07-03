package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import org.testng.asserts.SoftAssert;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class CalendarPage extends BasePage{

    private final SelenideElement GIVE_FEEDBACK_LINK = $x("//a[text()='Give Feedback']");
    private final SelenideElement CUSTOMER_SUPPORT_LINK = $x("//a[text()='Customer Support']");
    private final SelenideElement CALENDAR_CONTENT_BLOCK = $("#CalendarContent");
    private final SelenideElement TRAINING_CALENDAR_LINK = $x("//a[text()='Training Calendar']");

    private final SelenideElement MONTH_TITLE = $("#dpMonth");

    private final SelenideElement ADD_WORKOUT_BUTTON = $("#QuickAddToggle");
    private final SelenideElement WORKOUT_LIBRARY_BUTTON = $("#WorkoutLibAdd");
    private final SelenideElement FULL_WORKOUT_BUTTON = $("#FullAddBtn");
    private final SelenideElement ADD_GARMIN_BUTTON = $("#GarminAddBtn");
    private final ElementsCollection CALENDAR_CELLS = $$(".calendar-day");
    private final SelenideElement NAVIGATION_LINK_RIGHT = $(".cal_next");
    private final SelenideElement NAVIGATION_LINK_LEFT = $(".cal_prev");
    private final SelenideElement CELL_WITH_WORKOUT=  $x("//td[contains(@class, 'calendar-day')][.//div[contains(@class, 'calendarworkout')]]");
    private final SelenideElement CELL_WITHOUT_WORKOUT = $x("//td[contains(@class, 'calendar-day')][not(.//div[contains(@class, 'calendarworkout')])]");
    private final SelenideElement ACTIVITY_LABEL = $(".fc-event-activity-title");



    public CalendarPage goToNextMonth() {
        log.info("Нажать на правую стрелочку");
        NAVIGATION_LINK_RIGHT.shouldBe(visible).click();
        return this;
    }

    public CalendarPage goToPrevMonth() {
        NAVIGATION_LINK_LEFT.shouldBe(visible).click();
        return this;
    }


    public CalendarPage ckickGiveFeedBackLink() {
        log.info("Нажать на кнопку фидбека");
        GIVE_FEEDBACK_LINK.shouldBe(visible).click();
        return this;
    }

    public CalendarPage clickCustomerSupportLink() {
        log.info("Нажать на кнопку клиентской поддержки");
        GIVE_FEEDBACK_LINK.shouldBe(visible).click();
        return this;
    }

    @Override
    public CalendarPage isPageOpened() {
        log.info("Проверка, открыта ли страница календаря");
        CALENDAR_CONTENT_BLOCK.should(visible);
        TRAINING_CALENDAR_LINK.shouldBe(visible);
        return this;
    }


    public CalendarPage verifyCalendarBlock() {
        log.info("Проверка наличия в календаре кнопок добавления тренировок, заголовка, ячеек");
        MONTH_TITLE.shouldBe(visible);
        ADD_WORKOUT_BUTTON.shouldBe(visible);
        WORKOUT_LIBRARY_BUTTON.shouldBe(visible);
        FULL_WORKOUT_BUTTON.shouldBe(visible);
        ADD_GARMIN_BUTTON.shouldBe(visible);
        CALENDAR_CELLS.first().shouldBe(visible);
        CALENDAR_CELLS.shouldHave(sizeGreaterThan(28));
        return this;
    }


    public void verifyCurrentMonthAndYear() {
        log.info("Проверка актуального месяца и года в календаре");
               String expected = LocalDate.now()
                .format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
        MONTH_TITLE.shouldBe(visible).shouldHave(text(expected));
    }

    public CalendarPage goToNextMonthAndWaitForUpdate() {
        log.info("Проверка  месяца и года в календаре при нажатии по правой стрелочке");
        String nextMonth = LocalDate.now()
                .plusMonths(1)
                .format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
        MONTH_TITLE.shouldHave(com.codeborne.selenide.Condition.text(nextMonth));
        return this;
    }

    public CalendarPage goToPrevMonthAndWaitForUpdate() {
        log.info("Проверка  месяца и года в календаре при нажатии по левой стрелочке");
        String nextMonth = LocalDate.now()
                .minusMonths(1)
                .format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
        MONTH_TITLE.shouldHave(com.codeborne.selenide.Condition.text(nextMonth));
        return this;
    }

    public CalendarPage checkCellWithoutWorkout() {
        log.info("Проверка, что в ячейке без тренировки нет тренировки");
        CELL_WITHOUT_WORKOUT.shouldBe(visible);
        CELL_WITHOUT_WORKOUT
                .$x(".//div[contains(@class, 'calendarworkout')]")
                .shouldNot(exist);
        return this;
    }

    public CalendarPage checkCellWithtWorkout() {
        log.info("Проверка, что в ячейке без тренировки нет тренировки");
        CELL_WITH_WORKOUT.shouldBe(visible);
        $x(".//div[contains(@class, 'calendarworkout')]").should(exist);
        return this;
    }

    public CalendarPage checkActivityLabelExistence() {
        log.info("Проверка наличия метки в тренировке");
        CELL_WITH_WORKOUT.shouldBe(visible);
        CELL_WITH_WORKOUT
                .$x(".//div[@class='fc-event-activity-title']").should(exist);
        return this;
    }


    private String hexToRgb(String hex) {
        if (hex.startsWith("#")) {
            hex = hex.substring(1);
        }
        int r = Integer.parseInt(hex.substring(0, 2), 16);
        int g = Integer.parseInt(hex.substring(2, 4), 16);
        int b = Integer.parseInt(hex.substring(4, 6), 16);
        return String.format("rgb(%d, %d, %d)", r, g, b);
    }

    public CalendarPage checkColorInWorkout(String workoutType, String expectedColor) {
        log.info("Проверка цвета для тренировки типа '{}'", workoutType);
        SoftAssert softAssert = new SoftAssert();
        SelenideElement targetCell = CALENDAR_CELLS.findBy(Condition.text(workoutType));
        targetCell.shouldBe(visible);
        String actualColor = targetCell.$(".fc-event").getCssValue("background-color");
        String expected = hexToRgb(expectedColor);
        softAssert.assertEquals(expected, actualColor);
               return this;
    }
}
