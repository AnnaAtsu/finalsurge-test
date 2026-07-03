package pages;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class WorkoutsPage extends BasePage{

    private final SelenideElement CALENDAR_CONTENT_BLOCK = $("#CalendarContent");
    private final SelenideElement TRAINING_CALENDAR_LINK = $x("//a[text()='Training Calendar']");
    private final SelenideElement ADD_WORKOUT_BUTTON = $("#QuickAddToggle");
    private final SelenideElement WORKOUT_LIBRARY_BUTTON = $("#WorkoutLibAdd");
    private final SelenideElement FULL_WORKOUT_BUTTON = $("#FullAddBtn");
    private final SelenideElement WORKOUT_CALENDAR_INPUT = $("#WorkoutDate");
    private final SelenideElement DATEPICKER_CALENDAR = $(".datepicker.dropdown-menu");
    private final SelenideElement SIDE_CALENDAR_MONTH_TITLE = $("th.switch");
    private final SelenideElement ADD_ONN_CALENDAR_ICON = $("span.add-on .icon-calendar");
    private final SelenideElement ACTIVE_DAY = $(".day.active");
    private final SelenideElement ACTIVITY_SELECT = $("#ActivityType");
    private final SelenideElement WORKOUT_NAME_INPUT = $("#Name");
    private final SelenideElement WORKOUT_DESCRIPTION_INPUT = $("#Desc");
    private final SelenideElement WORKOUT_DISTANCE_INPUT = $("#Distance");
    private final SelenideElement WORKOUT_DURATION_INPUT = $("#Duration");
    private final SelenideElement WORKOUT_PACE_INPUT = $("#Pace");
    private final SelenideElement CANCEL_BUTTON = $("#CancelClose");
    private final SelenideElement SAVE_BUTTON = $("#saveButton");


    @Override
    public WorkoutsPage isPageOpened() {
        log.info("Проверка, открыта ли страница с тренировкой");
        ADD_WORKOUT_BUTTON.shouldBe(visible);
        WORKOUT_LIBRARY_BUTTON.shouldBe(visible);
        FULL_WORKOUT_BUTTON.shouldBe(visible);
        return this;
    }


    public WorkoutsPage clickAddWorkout() {
        log.info("Нажать на кнопку быстрого добавления тренировки");
        ADD_WORKOUT_BUTTON.shouldBe(visible).click();
        return this;
    }

    public WorkoutsPage verifyWorkoutCalendarInput() {
        log.info("Проверка наличия календаря в боковом меню тренировки");
        WORKOUT_CALENDAR_INPUT.should(visible);
        ADD_ONN_CALENDAR_ICON.shouldBe(visible).click();
        DATEPICKER_CALENDAR.shouldBe(visible, Duration.ofSeconds(5));
        return this;
    }

    public WorkoutsPage checkActualMonthSideCalendar() {
        log.info("Проверка актуального месяца и года в боковом календаре");
        String expected = LocalDate.now()
                .format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
        SIDE_CALENDAR_MONTH_TITLE.shouldBe(visible).shouldHave(text(expected));
        return this;
    }

    public WorkoutsPage clickActiveDay() {
        log.info("Нажать на активный день в календаре");
        ACTIVE_DAY.shouldBe(visible).click();
        DATEPICKER_CALENDAR.shouldBe(hidden, Duration.ofSeconds(3));
        return this;
    }

    public WorkoutsPage selectActivityType(String activityType) {
        log.info("Выбрать тип активности" + activityType);
        ACTIVITY_SELECT.shouldBe(visible)
                .selectOption(activityType);
        return this;
    }

    public WorkoutsPage verifySelectedActivity(String subActivity) {
        log.info("Проверка отображения типа активности" + subActivity);
        ACTIVITY_SELECT.shouldBe(visible)
                .selectOptionContainingText(subActivity);
               return this;
    }

    public WorkoutsPage fillinNameAndDescription() {
        log.info("Заполнить поля с именем и описанием");
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy_HH-mm-ss"));
        WORKOUT_NAME_INPUT.sendKeys("TestName" + dateTime);
        WORKOUT_DESCRIPTION_INPUT.sendKeys("TestDescription" + dateTime);
        return this;
    }
    public WorkoutsPage fillinDistanceDurationPace() {
        log.info("Заполнить поля с дистанцией, длительностью");
        WORKOUT_DISTANCE_INPUT.sendKeys("2");
        WORKOUT_DURATION_INPUT.sendKeys("010302");
        WORKOUT_PACE_INPUT.shouldBe(visible).shouldNotBe(empty);
        return this;
    }

    public WorkoutsPage clickCancelButton() {
        log.info("Нажать на кнопку Cancel");
        CANCEL_BUTTON.shouldBe(visible).click();
        return this;
    }

    public WorkoutsPage clickSaveWorkout() {
        log.info("Нажать на кнопку Add Workout");
    SAVE_BUTTON.shouldBe(visible).click();
        return this;
    }
}
