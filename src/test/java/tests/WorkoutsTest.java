package tests;

import dto.DailyVitals;
import dto.DailyVitalsFactory;
import dto.WorkOuts;
import dto.WorkOutsFactory;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import static com.codeborne.selenide.Selenide.title;
import static constants.ConstantElements.email;
import static constants.ConstantElements.password;
import static elements.Elements.*;
import static elements.Elements.titleForDashboardPage;

@Log4j2
public class WorkoutsTest extends BaseTest{


    @Test(testName = "Создание тренировки: выбор даты ", description = "Календарь выбора даты открывается, дата устанавливается корректно")
    public void checkCalendarActualMonthExistence() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        verifyUrl(urlCalendar);
        softAssert.assertEquals(title(), titleForDashboardPage);
        workoutsPage.clickAddWorkout();
        isTextDisplayed(WORKOUT_QUICK_ADD_TITLE);
        workoutsPage.verifyWorkoutCalendarInput();
        workoutsPage.checkActualMonthSideCalendar();
        softAssert.assertAll();
    }

    @Test(testName = "Создание тренировки: выбор типа активности  ", description = "Доступны типы: Run, Bike, Swim, Custom. Выбранный тип отображается в форме")
    public void checkActivityTypeExistence() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        verifyUrl(urlCalendar);
        softAssert.assertEquals(title(), titleForDashboardPage);
        workoutsPage.clickAddWorkout()
                .selectActivityType(WORKOUT_ACTIVITY_TYPE)
                .verifySelectedActivity(WORKOUT_ACTIVITY_TYPE);
        softAssert.assertAll();
    }
    @Test(testName = "Создание тренировки: заполнение названия и описания", description = "Поля Title и Description принимают текст, сохраняются после создания")
    public void checkTtitleDescriptionExistence() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        verifyUrl(urlCalendar);
        softAssert.assertEquals(title(), titleForDashboardPage);
        workoutsPage.clickAddWorkout()
                        .fillinNameAndDescription();
        isTextDisplayed(WORKOUT_NAME);
        isTextDisplayed(WORKOUT_DESCRIPTION);
        softAssert.assertAll();
    }

    @Test(testName = "Создание тренировки: добавление интервалов ", description = "Поля Distance, Duration, Pace заполняются и сохраняются корректно")
    public void checkIntervalExistence() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        verifyUrl(urlCalendar);
        softAssert.assertEquals(title(), titleForDashboardPage);
        workoutsPage.clickAddWorkout()
                    .fillinDistanceDurationPace();
        softAssert.assertAll();
    }

    @Test(testName = "Создание тренировки: нажать на Cancel ", description = "Нет бокового календаря ")
    public void checkCancelButton() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        verifyUrl(urlCalendar);
        softAssert.assertEquals(title(), titleForDashboardPage);
        workoutsPage.clickAddWorkout()
                    .clickCancelButton();
        isTextNotDisplayed(WORKOUT_QUICK_ADD_TITLE);
        softAssert.assertAll();
    }


    @Test(testName = "Создание тренировки с пустым названием ", description = "Отображается сообщение об ошибке `\"*Please select a valid Activity Type.\"`")
    public void checkEmptyNameWorkout() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        verifyUrl(urlCalendar);
        softAssert.assertEquals(title(), titleForDashboardPage);
        workoutsPage.clickAddWorkout()
                        .clickSaveWorkout();
        isTextDisplayed(ACTIVITY_ADD_ERROR_MESSAGE);
        softAssert.assertAll();
    }

    WorkOuts workOuts = WorkOutsFactory.getWorkOuts();
    @Test(testName = "Cоздание тренировки: добавление заметок (Comment) "
            , description = "Поле   принимает  текст, сохраняется ")
    public void checkCreateWorkout() {
        SoftAssert softAssert = new SoftAssert();
        WorkOuts workOuts = WorkOutsFactory.getWorkOuts();
        String expectedWorkoutName = workOuts.getName();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        verifyUrl(urlCalendar);
        softAssert.assertEquals(title(), titleForDashboardPage);
        workoutsPage.clickAddWorkout()
                .fillQuickWorkout(workOuts)
                .clickSaveWorkout();
        isTextDisplayed(expectedWorkoutName);
        softAssert.assertAll();
    }
}
