package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import dto.WorkOuts;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

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
    private final SelenideElement WORKOUT_NOTES_INPUT = $("#PostDesc");
    private final SelenideElement UPDATE_WORKOUT_BUTTON = $x("//ul[@role='menu']//a[@class='full-edit' and text()='Update Workout']");
    private final SelenideElement DELETE_WORKOUT_BUTTON = $x("//ul[@role='menu']//a[@class='quick-delete' and text()='Delete']");

    private final SelenideElement MODAL_WINDOW = $(".bootbox.modal.fade.in");
    private final SelenideElement OK_BUTTON_ON_MODAL_WINDOW = $x("//a[@class='btn btn-primary' and text()='OK']");
    private final SelenideElement CANCEL_BUTTON_ON_MODAL_WINDOW = $x("//a[@class='btn null' and text()='Cancel']");

    @Override
    @Step("Проверка, открыта ли страница с тренировкой")
    public WorkoutsPage isPageOpened() {
        log.info("Проверка, открыта ли страница с тренировкой");
        ADD_WORKOUT_BUTTON.shouldBe(visible);
        WORKOUT_LIBRARY_BUTTON.shouldBe(visible);
        FULL_WORKOUT_BUTTON.shouldBe(visible);
        return this;
    }


    @Step("Нажать на кнопку быстрого добавления тренировки")
    public WorkoutsPage clickAddWorkout() {
        log.info("Нажать на кнопку быстрого добавления тренировки");
        ADD_WORKOUT_BUTTON.shouldBe(visible).click();
        return this;
    }

    @Step("Проверка наличия календаря в боковом меню тренировки")
    public WorkoutsPage verifyWorkoutCalendarInput() {
        log.info("Проверка наличия календаря в боковом меню тренировки");
        WORKOUT_CALENDAR_INPUT.should(visible);
        ADD_ONN_CALENDAR_ICON.shouldBe(visible).click();
        DATEPICKER_CALENDAR.shouldBe(visible, Duration.ofSeconds(5));
        return this;
    }

    @Step("Проверка актуального месяца и года в боковом календаре")
    public WorkoutsPage checkActualMonthSideCalendar() {
        log.info("Проверка актуального месяца и года в боковом календаре");
        String expected = LocalDate.now()
                .format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));
        SIDE_CALENDAR_MONTH_TITLE.shouldBe(visible).shouldHave(text(expected));
        return this;
    }

    @Step("Нажать на активный день в календаре")
    public WorkoutsPage clickActiveDay() {
        log.info("Нажать на активный день в календаре");
        ACTIVE_DAY.shouldBe(visible).click();
        DATEPICKER_CALENDAR.shouldBe(hidden, Duration.ofSeconds(3));
        return this;
    }

    @Step("Выбрать тип активности")
    public WorkoutsPage selectActivityType(String activityType) {
        log.info("Выбрать тип активности" + activityType);
        ACTIVITY_SELECT.shouldBe(visible)
                .selectOption(activityType);
        return this;
    }

    @Step("Проверка отображения типа активности")
    public WorkoutsPage verifySelectedActivity(String subActivity) {
        log.info("Проверка отображения типа активности " + subActivity);
        ACTIVITY_SELECT.shouldBe(visible)
                .selectOptionContainingText(subActivity);
               return this;
    }

    @Step("Заполнить поля с именем и описанием")
    public WorkoutsPage fillinNameAndDescription() {
        log.info("Заполнить поля с именем и описанием");
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy_HH-mm-ss"));
        WORKOUT_NAME_INPUT.sendKeys("TestName" + dateTime);
        WORKOUT_DESCRIPTION_INPUT.sendKeys("TestDescription" + dateTime);
        return this;
    }
    @Step("Заполнить поля с дистанцией, длительностью")
    public WorkoutsPage fillinDistanceDurationPace() {
        log.info("Заполнить поля с дистанцией, длительностью");
        WORKOUT_DISTANCE_INPUT.sendKeys("2");
        WORKOUT_DURATION_INPUT.sendKeys("010302");
        WORKOUT_PACE_INPUT.shouldBe(visible).shouldNotBe(empty);
        return this;
    }

    @Step("Нажать на кнопку Cancel")
    public WorkoutsPage clickCancelButton() {
        log.info("Нажать на кнопку Cancel");
        CANCEL_BUTTON.shouldBe(visible).click();
        return this;
    }

    @Step("Нажать на кнопку Add Workout")
    public WorkoutsPage clickSaveWorkout() {
        log.info("Нажать на кнопку Add Workout");
    SAVE_BUTTON.shouldBe(visible).click();
        return this;
    }

    @Step("Нажать на первую тренировку в календаре, открыть её для редактирования и запомнить название")
    public WorkoutsPage clickWorkoutAndUpdate() {
        log.info("Нажать на кнопку Update Workout");
        ElementsCollection items = $$("div.fc-event-activity-title").filter(visible);
        items.shouldHave(CollectionCondition.sizeGreaterThan(0)); // ждем, что элемент есть
        int randomIndex = new Random().nextInt(items.size());
        SelenideElement randomElement = items.get(randomIndex);
        String workoutText = randomElement.getText();
        randomElement.click();
        System.out.println("Клик по элементу с текстом: " + workoutText);
        return this;
    }

    @Step("Нажать на кнопку обновления тренировки")
    public WorkoutsPage clickButtonForDeleteWorkout() {
        log.info("Нажать на кнопку Delete Workout");
        executeJavaScript("arguments[0].click()", DELETE_WORKOUT_BUTTON);
        return this;
    }

    @Step("Нажать на кнопку тренировки")
    public WorkoutsPage clickButtonForWorkoutBlock() {
        log.info("Нажать на кнопку Update Workout");
        executeJavaScript("arguments[0].click()", UPDATE_WORKOUT_BUTTON);
        return this;
    }

    @Step("Редактирование витальной тренировки '{}'")
    public WorkoutsPage fillQuickWorkout(WorkOuts workOuts) {
        log.info("Редактирование витальной тренировки '{}'", workOuts.getName());
        WORKOUT_NAME_INPUT.setValue(workOuts.getName());
        WORKOUT_DESCRIPTION_INPUT.setValue(workOuts.getDescription());
        WORKOUT_DISTANCE_INPUT.setValue(workOuts.getDistance());
        WORKOUT_PACE_INPUT.setValue(workOuts.getPace());
        WORKOUT_CALENDAR_INPUT.setValue(workOuts.getDate());
        ACTIVITY_SELECT.selectOptionContainingText("Walk");
        WORKOUT_NOTES_INPUT.setValue(workOuts.getNotes());
        return this;
    }

    @Step("Редактирование витальной тренировки '{}'")
    public WorkoutsPage editQuickWorkout(WorkOuts workOuts) {
        log.info("Редактирование готовой витальной тренировки '{}'", workOuts.getName());
        WORKOUT_NAME_INPUT.clear();
        WORKOUT_NAME_INPUT.setValue(workOuts.getName());
        WORKOUT_DESCRIPTION_INPUT.clear();
        WORKOUT_DESCRIPTION_INPUT.setValue(workOuts.getDescription());
        return this;
    }

    @Step("Нажать на кнопку OK для подтверждения удаления тренировки")
    public WorkoutsPage clickOKButtonForDelete() {
        log.info("Нажать на OK для подтверждения удаления тренировки");
        MODAL_WINDOW.shouldBe(visible, Duration.ofSeconds(10));
        OK_BUTTON_ON_MODAL_WINDOW.shouldBe(visible).click();
        return this;
    }

    @Step("Нажать на кнопку Cancel для отмены удаления тренировки")
    public WorkoutsPage clickCancelButtonForDelete() {
        log.info("Нажать на Cancel для отмены удаления тренировки");
        MODAL_WINDOW.shouldBe(visible, Duration.ofSeconds(10));
        CANCEL_BUTTON_ON_MODAL_WINDOW.shouldBe(visible).click();
        return this;
    }
}
