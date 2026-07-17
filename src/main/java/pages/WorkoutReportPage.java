package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static elements.Elements.WORKOUT_REPORT_MESSAGE_FOR_NO_FILTER;
import static urls.Urls.reportEndpoint;

@Log4j2
public class WorkoutReportPage extends BasePage{

    private final SelenideElement VIEW_REPORT_BUTTON  = $("#saveButton");
    private final SelenideElement START_DATE_FIELD  = $("#WorkoutDate");
    private final SelenideElement END_DATE_FIELD  = $("#WorkoutDateEnd");
    private final SelenideElement ACTIVITY_TYPE_MENU  = $("#ActivityType");
    private final SelenideElement GROUP_BY_WEEK_RADIO_BUTTON = $("#groupBy2");
    private final SelenideElement TRAINIG_BLOCK = $(".table-striped.table-condensed");
    private final SelenideElement GROUP_BY_ACTIVITY_RADIO_BUTTON = $("#groupBy4");
    private final SelenideElement LEAVE_COMMENT_ICON = $(".icsw16-create-write");
    private final SelenideElement COMMENT_TEXTAREA = $("#CommentDesc");
    private final SelenideElement ADD_COMMENT_BUTTON  = $("#saveButtonComment");
    private final SelenideElement COMMENT_IFRAME = $("#WorkoutCommentsiFrame");

    @Override
    @Step("Проверка, открыта ли страница репорта")
    public WorkoutReportPage isPageOpened() {
        log.info("Проверка, открыта ли страница репорта");
        VIEW_REPORT_BUTTON.shouldBe(visible);
        START_DATE_FIELD.shouldBe(visible);
        END_DATE_FIELD.shouldBe(visible);
        ACTIVITY_TYPE_MENU.shouldBe(visible);
        GROUP_BY_WEEK_RADIO_BUTTON.shouldBe(visible);
        return this;
    }

    @Step("Открыть страницу репорта")
    public WorkoutReportPage openWorkoutReportPage() {
        log.info("Открыть страницу репорта");
        Selenide.open(reportEndpoint);
        return this;
    }

    @Step("Нажать на кнопку отображения отчета")
    public WorkoutReportPage clickViewReportbutton() {
        log.info("Нажать на кнопку отображения отчета");
        VIEW_REPORT_BUTTON.shouldBe(visible).click();
        return this;
    }

    @Step("Установить Start Date: {}")
    public WorkoutReportPage setStartDate(LocalDate date) {
        log.info("Установить Start Date: {}", date);
        START_DATE_FIELD.shouldBe(visible).clear();
        START_DATE_FIELD.sendKeys(date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        return this;
    }

    @Step("Установить End Date: {}")
    public WorkoutReportPage setEndDate(LocalDate date) {
        log.info("Установить End Date: {}", date);
        END_DATE_FIELD.shouldBe(visible).clear();
        END_DATE_FIELD.sendKeys(date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        return this;
    }

    @Step("Нажать на радиобатон группировки по неделе")
    public WorkoutReportPage chooseGroupByWeekButton() {
        log.info("Нажать на радиобатон группировки по неделе");
        GROUP_BY_WEEK_RADIO_BUTTON.shouldBe(visible);
        GROUP_BY_WEEK_RADIO_BUTTON.click();
        return this;
    }
    @Step("Проверка отображения блока с тренировками")
    public WorkoutReportPage vefifyTrainigblockExistence() {
        log.info("Проверка отображения блока с тренировками");
        TRAINIG_BLOCK.shouldBe(visible);
        return this;
    }

    @Step("Нажать на радиобатон группировки по активности")
    public WorkoutReportPage chooseGroupByActivityButton() {
        log.info("Нажать на радиобатон группировки по активности");
        GROUP_BY_ACTIVITY_RADIO_BUTTON.shouldBe(visible);
        GROUP_BY_ACTIVITY_RADIO_BUTTON.click();
        return this;
    }

    @Step("Проверка заголовка h4 с типом активности: {}")
    public WorkoutReportPage verifyActivityHeader(String activityType) {
        log.info("Проверка заголовка h4 с типом активности: {}", activityType);
        $x("//h4[contains(text(), '" + activityType + "')]").shouldBe(visible);
        return this;
    }

    @Step("Нажать на иконку комментария")
    public WorkoutReportPage clickCommentIcon() {
        log.info("Нажать на иконку комментария");
        LEAVE_COMMENT_ICON.shouldBe(visible).shouldBe(enabled);
        LEAVE_COMMENT_ICON.click();
        return this;
    }

    @Step("Добавление комментария")
    public WorkoutReportPage fillComment(String message) {
        log.info("Добавление комментария");
        COMMENT_IFRAME.shouldBe(visible);
        switchTo().frame("WorkoutCommentsiFrame");
        COMMENT_TEXTAREA.shouldBe(visible).setValue(message);
        ADD_COMMENT_BUTTON.shouldBe(visible).click();
        switchTo().defaultContent();
        return this;
    }

}
