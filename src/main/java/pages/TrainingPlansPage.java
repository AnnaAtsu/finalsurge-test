package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;

import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class TrainingPlansPage extends BasePage{
    private final SelenideElement TRAINING_PLAN_HEADER = $("#AboutPlansHeader");
    private final SelenideElement TRAINING_PLAN_CONTENT = $("#AboutPlansContent");
    private final SelenideElement FIND_PLAN_BUTTON = $x("//a[text()='Find a Training Plan!']");
    private final SelenideElement PLAN_HISTORY_BUTTON = $x("//button[text()='Plan History']");
    private final SelenideElement SPORT_SELECT = $("input[placeholder='All Sports']");
    private final SelenideElement SEARCH_INPUT = $("input[placeholder='Search by title, coach, description']");
    private final SelenideElement TRAINING_PLAN_LIST_ITEM = $x("//a[@class='training-plan-list-item']");
    private final SelenideElement TRAINING_PLAN_LIST_ITEM_FIRST = $$x("//a[contains(@class, 'training-plan-list-item')]").first();

    @Override
    @Step("Проверка, открыта ли страница с планом тренировки")
    public TrainingPlansPage isPageOpened() {
        log.info("Проверка, открыта ли страница c планом тренировки");
        TRAINING_PLAN_HEADER.shouldBe(visible);
        FIND_PLAN_BUTTON.shouldBe(visible);
        PLAN_HISTORY_BUTTON.shouldBe(visible);
        return this;
    }

    @Step("Открыть страницу с планом тренировки")
    public TrainingPlansPage openPage() {
        log.info("Открыть страницу с планом тренировки");
        Selenide.open("/TrainingPlans.cshtml");
        return this;
    }

    @Step("Нажать на кнопку поиска планов Find a Training Plan!")
    public void clickFindPlanButton() {
        log.info("Нажать на кнопку поиска планов Find a Training Plan!");
        FIND_PLAN_BUTTON.shouldBe(visible, Duration.ofSeconds(30)).click();
    }

    @Step("Проверка списка чужих тренировок")
    public void verifyTrainingPlans() {
        log.info("Проверка списка чужих тренировок");
        refresh();
        SPORT_SELECT.shouldBe(visible, Duration.ofSeconds(10));
        SEARCH_INPUT.shouldBe(visible, Duration.ofSeconds(10));
        TRAINING_PLAN_LIST_ITEM.shouldBe(visible, Duration.ofSeconds(10));
    }

    @Step("Нажать на кнопку конкретного плана")
    public void clickFindPlanITEMButton() {
        log.info("Нажать на кнопку конкретного плана");
        TRAINING_PLAN_LIST_ITEM_FIRST.shouldBe(visible).click();
    }



    @Step("Нажать на кнопку Plan History")
    public void clickFindHistoryPlanButton() {
        log.info("Нажать на кнопку Plan History");
        PLAN_HISTORY_BUTTON.shouldBe(visible).click();
    }
}
