package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import dto.DailyVitals;
import dto.Feedback;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Random;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@Log4j2

public class DailyVitalsPage extends BasePage{

    private final SelenideElement ADD_VITALS_BUTTON  = $x("//button[@title='Add Vitals']");
    private final SelenideElement PAST_DAYS_SELECT  = $("#PastDays");
    private final SelenideElement TABLE =
            $("table.table.table-striped.table-condensed");
    private final SelenideElement SAVE_VITALS_BUTTON  = $("#saveButton");
    private final SelenideElement VITALS_DATE_FIELD  = $("#VitalsDate");
    private final SelenideElement VITALS_WEIGHT_FIELD  = $("#Weight");
    private final SelenideElement VITALS_STEPS_FIELD  = $("#Steps");
    private final SelenideElement VITALS_CALORIE_CONSUMED_FIELD  = $("#Calories");
    private final SelenideElement VITALS_BODY_FAT_FIELD  = $("#BodyFat");
    private final SelenideElement VITALS_WATER_FIELD  = $("#WaterPercent");
    private final SelenideElement VITALS_RESTINGHR_FIELD  = $("#RestHR");
    private final SelenideElement VITALS_HRVARIABILITY_FIELD  = $("#HRVar");
    private final SelenideElement VITALS_HOURS_SLEEP_FIELD  = $("#SleepHours");
    private final SelenideElement VITALS_HEALTH_NOTES_FIELD  = $("#HealthNotes");
    private final SelenideElement VITALS_SLEEP_AMOUNT_SELECT  = $("#SleepAmount");
    private final SelenideElement VITALS_SLEEP_QUALITY_SELECT  = $("#SleepQuality");
    private final SelenideElement HIGH_CHART_TABLE  = $("rect.highcharts-background");
    private final SelenideElement WEIGHT_INDICATOR
            = $x("//*[local-name()='text' and text()='Weight']");
    private final SelenideElement SLEEP_INDICATOR
            = $x("//*[local-name()='text' and text()='Sleep']");
    private final SelenideElement CALORIES_INDICATOR
            = $x("//*[local-name()='text' and text()='Calories']");
    private final SelenideElement HR_INDICATOR
            = $x("//*[local-name()='g' and contains(@class,'highcharts-legend-item')]//*[local-name()='tspan' and normalize-space()='Resting HR']");
    private final SelenideElement STEPS_INDICATOR
            = $x("//*[local-name()='text' and text()='Steps']");
    private final SelenideElement HRV_INDICATOR
            = $x("//*[local-name()='text' and text()='HRV']");
    SelenideElement LINE =
            $x("//*[local-name()='path' and @stroke]");


    public DailyVitalsPage openPage() {
        log.info("Открыть страницу ежедневных тренировок");
        Selenide.open("/DailyVitals.cshtml");
        return this;
    }
    @Override
    public DailyVitalsPage isPageOpened() {
        log.info("Проверка, что страница ежедневных тренировок открыта");
        ADD_VITALS_BUTTON.shouldBe(visible);
        PAST_DAYS_SELECT.shouldBe(visible);
        TABLE.shouldBe(visible);
        return this ;
    }

    public String selectRandomTimePeriod() {
        log.info("Отредактировать настройки профиля");
        List<String> sports = PAST_DAYS_SELECT.$$("option")
                .texts();
        sports.remove(0);
        String randomPeriod = sports.get(new Random().nextInt(sports.size()));
        PAST_DAYS_SELECT.selectOption(randomPeriod);
        return randomPeriod;
    }

    public DailyVitalsPage clickAddVitalButton() {
        log.info("Нажать на кнопку добавления показателей");
        ADD_VITALS_BUTTON.shouldBe(visible).click();
        return this;
    }

    public DailyVitalsPage openeditedPage() {
        log.info("Открыть отредактированную страницу ежедневных тренировок");
        Selenide.open("/DailyVitals?vitalsdate=7/12/2026&edit=1");
        return this;
    }

    public DailyVitalsPage clickSaveVitalButton() {
        log.info("Нажать на кнопку сохранения показателей");
        SAVE_VITALS_BUTTON.shouldBe(visible).click();
        return this;
    }

    public DailyVitalsPage fillVital(DailyVitals dailyVitalsk) {
        log.info("Создание витальной тренировки '{}'", dailyVitalsk.getHealthNotes());
        VITALS_DATE_FIELD.setValue(dailyVitalsk.getDate());
        VITALS_STEPS_FIELD.setValue(dailyVitalsk.getSteps());
        VITALS_CALORIE_CONSUMED_FIELD.setValue(dailyVitalsk.getCaloriesConsumed());
        VITALS_WEIGHT_FIELD.setValue(dailyVitalsk.getWeight());
        VITALS_BODY_FAT_FIELD.setValue(dailyVitalsk.getBodyFat());
        VITALS_WATER_FIELD.setValue(dailyVitalsk.getWater());
        VITALS_RESTINGHR_FIELD.setValue(dailyVitalsk.getRestingHR());
        VITALS_HRVARIABILITY_FIELD.setValue(dailyVitalsk.getHRVariability());
        VITALS_HOURS_SLEEP_FIELD.setValue(dailyVitalsk.getSleepHours());
        VITALS_HEALTH_NOTES_FIELD.setValue(dailyVitalsk.getHealthNotes());
        VITALS_SLEEP_AMOUNT_SELECT.selectOptionContainingText("Enough");
        VITALS_SLEEP_QUALITY_SELECT.selectOptionContainingText("Moderate");
        return this;
    }

    public DailyVitalsPage clickIconsExceptWeight() {
        log.info("Нажать все графы, кроме графы веса");
        PAST_DAYS_SELECT.selectOptionContainingText("This Year");
        HIGH_CHART_TABLE.shouldBe(visible);
        STEPS_INDICATOR.shouldBe(visible).click();
        SLEEP_INDICATOR.shouldBe(visible).click();
        HR_INDICATOR.shouldBe(visible).click();
        HRV_INDICATOR.shouldBe(visible).click();
        CALORIES_INDICATOR.shouldBe(visible).click();
        WEIGHT_INDICATOR.shouldBe(visible);
        LINE.shouldHave(attribute("stroke", "#eb365d"));
        return this;
    }

    public DailyVitalsPage editVital(DailyVitals dailyVitalsk) {
        log.info("Редактирование витальной тренировки '{}'", dailyVitalsk.getHealthNotes());
        VITALS_DATE_FIELD.setValue(dailyVitalsk.getDate());
        VITALS_STEPS_FIELD.clear();
        VITALS_STEPS_FIELD.setValue(dailyVitalsk.getSteps());
        VITALS_CALORIE_CONSUMED_FIELD.clear();
        VITALS_CALORIE_CONSUMED_FIELD.setValue(dailyVitalsk.getCaloriesConsumed());
        VITALS_WEIGHT_FIELD.clear();
        VITALS_WEIGHT_FIELD.setValue(dailyVitalsk.getWeight());
        VITALS_BODY_FAT_FIELD.clear();
        VITALS_BODY_FAT_FIELD.setValue(dailyVitalsk.getBodyFat());
        VITALS_WATER_FIELD.clear();
        VITALS_WATER_FIELD.setValue(dailyVitalsk.getWater());
        VITALS_RESTINGHR_FIELD.clear();
        VITALS_RESTINGHR_FIELD.setValue(dailyVitalsk.getRestingHR());
        VITALS_HRVARIABILITY_FIELD.clear();
        VITALS_HRVARIABILITY_FIELD.setValue(dailyVitalsk.getHRVariability());
        VITALS_HOURS_SLEEP_FIELD.clear();
        VITALS_HOURS_SLEEP_FIELD.setValue(dailyVitalsk.getSleepHours());
        VITALS_HEALTH_NOTES_FIELD.clear();
        VITALS_HEALTH_NOTES_FIELD.setValue(dailyVitalsk.getHealthNotes());
        return this;
    }

    public DailyVitalsPage clickIconsExceptRestingHR() {
        log.info("Нажать все графы, кроме графы HR");
        PAST_DAYS_SELECT.selectOptionContainingText("This Year");
        HIGH_CHART_TABLE.shouldBe(visible);
        STEPS_INDICATOR.shouldBe(visible).click();
        SLEEP_INDICATOR.shouldBe(visible).click();
        HRV_INDICATOR.shouldBe(visible).click();
        CALORIES_INDICATOR.shouldBe(visible).click();
        WEIGHT_INDICATOR.shouldBe(visible).click();
        HR_INDICATOR.shouldBe(visible);
        LINE.shouldHave(attribute("stroke", "#eb365d"));
        return this;
    }
}
