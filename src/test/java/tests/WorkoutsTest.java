package tests;

import dto.WorkOuts;
import dto.WorkOutsFactory;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import static com.codeborne.selenide.Selenide.title;
import static constants.ConstantElements.email;
import static constants.ConstantElements.password;
import static elements.Elements.*;
import static elements.Elements.titleForDashboardPage;
import static urls.Urls.urlCalendar;

@Log4j2
public class WorkoutsTest extends BaseTest{

    @Test(testName = "Создание тренировки: выбор даты ", description = "Календарь выбора даты открывается, дата устанавливается корректно")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Workouts component")
    public void checkCalendarActualMonthExistence() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        urlAssertion.verifyUrl(urlCalendar);
        softAssert.assertEquals(title(), titleForDashboardPage);
        workoutsPage.clickAddWorkout();
        textAssertion.isTextDisplayed(WORKOUT_QUICK_ADD_TITLE);
        workoutsPage.verifyWorkoutCalendarInput();
        workoutsPage.checkActualMonthSideCalendar();
        softAssert.assertAll();
    }

    @Test(testName = "Создание тренировки: выбор типа активности  ", description = "Доступны типы: Run, Bike, Swim, Custom. Выбранный тип отображается в форме")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Workouts component")
    public void checkActivityTypeExistence() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        urlAssertion.verifyUrl(urlCalendar);
        softAssert.assertEquals(title(), titleForDashboardPage);
        workoutsPage.clickAddWorkout()
                .selectActivityType(WORKOUT_ACTIVITY_TYPE)
                .verifySelectedActivity(WORKOUT_ACTIVITY_TYPE);
    }
    @Test(testName = "Создание тренировки: заполнение названия и описания", description = "Поля Title и Description принимают текст, сохраняются после создания")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Workouts component")
    public void checkTtitleDescriptionExistence() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        urlAssertion.verifyUrl(urlCalendar);
        softAssert.assertEquals(title(), titleForDashboardPage);
        workoutsPage.clickAddWorkout()
                        .fillinNameAndDescription();
        textAssertion.isTextDisplayed(WORKOUT_NAME);
        textAssertion.isTextDisplayed(WORKOUT_DESCRIPTION);
    }

    @Test(testName = "Создание тренировки: добавление интервалов ", description = "Поля Distance, Duration, Pace заполняются и сохраняются корректно")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Workouts component")
    public void checkIntervalExistence() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        urlAssertion.verifyUrl(urlCalendar);
        softAssert.assertEquals(title(), titleForDashboardPage);
        workoutsPage.clickAddWorkout()
                    .fillinDistanceDurationPace();
        softAssert.assertAll();
    }

    @Test(testName = "Создание тренировки: нажать на Cancel ", description = "Нет бокового календаря ")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Workouts component")
    public void checkCancelButton() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        urlAssertion.verifyUrl(urlCalendar);
        softAssert.assertEquals(title(), titleForDashboardPage);
        workoutsPage.clickAddWorkout()
                    .clickCancelButton();
        textAssertion.isTextNotDisplayed(WORKOUT_QUICK_ADD_TITLE);
        softAssert.assertAll();
    }


    @Test(testName = "Создание тренировки с пустым названием ", description = "Отображается сообщение об ошибке `\"*Please select a valid Activity Type.\"`")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Workouts component")
    public void checkEmptyNameWorkout() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        urlAssertion.verifyUrl(urlCalendar);
        softAssert.assertEquals(title(), titleForDashboardPage);
        workoutsPage.clickAddWorkout()
                        .clickSaveWorkout();
        softAssert.assertTrue(textAssertion.isTextDisplayed(ACTIVITY_ADD_ERROR_MESSAGE));
        softAssert.assertAll();
    }

    @Test(testName = "Cоздание тренировки: добавление заметок (Comment) "
            , description = "Поле   принимает  текст, сохраняется ")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Workouts component")
    public void checkCreateWorkout() {
        SoftAssert softAssert = new SoftAssert();
        WorkOuts workOuts = WorkOutsFactory.getWorkOuts();
        String expectedWorkoutName = workOuts.getName();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        urlAssertion.verifyUrl(urlCalendar);
        softAssert.assertEquals(title(), titleForDashboardPage);
        workoutsPage.clickAddWorkout()
                .fillQuickWorkout(workOuts)
                .clickSaveWorkout();
        softAssert.assertTrue(textAssertion.isTextDisplayed(expectedWorkoutName));
        softAssert.assertAll();
    }

    @Test(testName = "Редактирование тренировки"
            , description = "После изменения полей и сохранения данные обновляются")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Workouts component")
    public void checkUpdateWorkout() {
        SoftAssert softAssert = new SoftAssert();
        WorkOuts workOuts = WorkOutsFactory.getWorkOuts();
        String expectedWorkoutName = workOuts.getName();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        urlAssertion.verifyUrl(urlCalendar);
        softAssert.assertEquals(title(), titleForDashboardPage);
        workoutsPage.clickWorkoutAndUpdate()
                .clickButtonForWorkoutBlock()
                .editQuickWorkout(workOuts)
                .clickSaveWorkout();
        softAssert.assertTrue(textAssertion.isTextDisplayed(expectedWorkoutName));
        softAssert.assertTrue(textAssertion.isTextDisplayed("Update Workout"));
        softAssert.assertAll();
    }

    @Test(testName = "Удаление тренировки"
            , description = "Появляется диалог подтверждения; после подтверждения тренировка удаляется из списка")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Workouts component")
    public void checkDeleteWorkout() {
        SoftAssert softAssert = new SoftAssert();
        WorkOuts workOuts = WorkOutsFactory.getWorkOuts();
        String expectedWorkoutName = workOuts.getName();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        urlAssertion.verifyUrl(urlCalendar);
        softAssert.assertEquals(title(), titleForDashboardPage);
        workoutsPage.clickWorkoutAndUpdate()
                .clickButtonForDeleteWorkout();
                textAssertion.isTextDisplayed(DELETE_WORKOUT_CALENDAR_MESSAGE);
        workoutsPage.clickOKButtonForDelete();
        textAssertion.isTextNotDisplayed(expectedWorkoutName);
    }

    @Test(testName = "Удаление тренировки: отмена"
            , description = "Появляется диалог подтверждения; после Cancel тренировка не удаляется")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Workouts component")
    public void checkCancelDeleteWorkout() {
        SoftAssert softAssert = new SoftAssert();
        WorkOuts workOuts = WorkOutsFactory.getWorkOuts();
        String expectedWorkoutName = workOuts.getName();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        urlAssertion.verifyUrl(urlCalendar);
        softAssert.assertEquals(title(), titleForDashboardPage);
        workoutsPage.clickWorkoutAndUpdate()
                .clickButtonForDeleteWorkout();
        textAssertion.isTextDisplayed(DELETE_WORKOUT_CALENDAR_MESSAGE);
        workoutsPage.clickCancelButtonForDelete();
        textAssertion.isTextDisplayed(expectedWorkoutName);
    }
}
