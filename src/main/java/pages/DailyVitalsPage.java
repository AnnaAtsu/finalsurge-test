package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Random;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@Log4j2

public class DailyVitalsPage extends BasePage{

    private final SelenideElement ADD_VITALS_BUTTON  = $x("//button[@title='Add Vitals']");
    private final SelenideElement PAST_DAYS_SELECT  = $("#PastDays");
    private final SelenideElement TABLE =
            $("table.table.table-striped.table-condensed");
    private final SelenideElement SAVE_VITALS_BUTTON  = $("#saveButton");

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

    public DailyVitalsPage clickSaveVitalButton() {
        log.info("Нажать на кнопку сохранения показателей");
        SAVE_VITALS_BUTTON.shouldBe(visible).click();
        return this;
    }
}
