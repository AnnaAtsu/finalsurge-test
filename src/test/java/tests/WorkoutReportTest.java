package tests;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;

import static com.codeborne.selenide.Selenide.title;
import static elements.Elements.*;

public class WorkoutReportTest extends BaseTest{

    @DataProvider(name="defaultDates")
    public Object[][] defaultDates() {
        return new Object[][] {
                {LocalDate.of(2026,7,5),LocalDate.of(2026,7, 10)}
        };
    }
    @Test(testName = "Отображение сводки тренировок за период", dataProvider = "defaultDates", description = "Таблица с агрегированными данными: кол-во тренировок, общая дистанция, длительность")
    public void checkReportForListView(LocalDate startDate, LocalDate endDate) {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        workoutReportPage.openWorkoutReportPage();
        isTextDisplayed(WORKOUT_REPORT_MESSAGE_FOR_NO_FILTER);
        workoutReportPage.isPageOpened()
                .setStartDate(startDate)
                .setEndDate(endDate)
                .clickViewReportbutton()
                .vefifyTrainigblockExistence();
        verifyUrl(urlWorkoutReport);
        isTextNotDisplayed(WORKOUT_REPORT_MESSAGE_FOR_NO_FILTER);
        isTextDisplayed(WORKOUT_REPORT_HEADER_FOR_LIST_VIEW);
        softAssert.assertEquals(title(), titleForWorkoutReportPage);
        softAssert.assertAll();
    }

    @DataProvider(name="weekDates")
    public Object[][] weekDates() {
        return new Object[][] {
                {LocalDate.of(2026,7,5),LocalDate.of(2026,7, 12)}
        };
    }
    @Test(testName = "Фильтрация отчетов по дате ", dataProvider = "weekDates",description = "При выборе даты данные на графиках и в таблице фильтруются по неделе")
    public void checkReportForWeek(LocalDate startDate, LocalDate endDate) {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        workoutReportPage.openWorkoutReportPage();
        isTextDisplayed(WORKOUT_REPORT_MESSAGE_FOR_NO_FILTER);
        workoutReportPage.isPageOpened()
                .setStartDate(startDate)
                .setEndDate(endDate)
                .chooseGroupByWeekButton()
                .clickViewReportbutton()
                .vefifyTrainigblockExistence();
        verifyUrl(urlWorkoutReport);
        isTextNotDisplayed(WORKOUT_REPORT_MESSAGE_FOR_NO_FILTER);
        isTextDisplayed(WORKOUT_REPORT_HEADER_FOR_GROUP_BY_WEEK);
        softAssert.assertEquals(title(), titleForWorkoutReportPage);
        softAssert.assertAll();
    }

    @Test(testName = "Фильтрация отчетов по типу активности", dataProvider = "weekDates", description = "При выборе типа активности данные на графиках и в таблице фильтруются")
    public void checkReportForActivity(LocalDate startDate, LocalDate endDate) {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        workoutReportPage.openWorkoutReportPage();
        verifyUrl(urlWorkoutReport);
        isTextDisplayed(WORKOUT_REPORT_MESSAGE_FOR_NO_FILTER);
        workoutReportPage.isPageOpened();
        workoutsPage.selectActivityType(WORKOUT_ACTIVITY_TYPE);
        workoutReportPage
                .setStartDate(startDate)
                .setEndDate(endDate)
                .clickViewReportbutton()
                .vefifyTrainigblockExistence();
        isTextNotDisplayed(WORKOUT_REPORT_MESSAGE_FOR_NO_FILTER);
        isTextDisplayed(WORKOUT_REPORT_MESSAGE_FOR_NO_FILTER);
        isTextDisplayed(WORKOUT_ACTIVITY_TYPE);
        softAssert.assertEquals(title(), titleForWorkoutReportPage);
        softAssert.assertAll();
    }

    @Test(testName = "Группировка активностей", dataProvider = "weekDates",description = "График показывает длительность тренировок по месяцам / неделям")
    public void checkReportForActivityButton(LocalDate startDate, LocalDate endDate) {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        workoutReportPage.openWorkoutReportPage();
        isTextDisplayed(WORKOUT_REPORT_MESSAGE_FOR_NO_FILTER);
        workoutReportPage.isPageOpened()
                .setStartDate(startDate)
                .setEndDate(endDate)
                .chooseGroupByActivityButton()
                .clickViewReportbutton()
                .vefifyTrainigblockExistence()
                .verifyActivityHeader(WORKOUT_ACTIVITY_TYPE);
        verifyUrl(urlWorkoutReport);
        isTextNotDisplayed(WORKOUT_REPORT_MESSAGE_FOR_NO_FILTER);
        isTextDisplayed(WORKOUT_REPORT_HEADER_FOR_GROUP_BY_WEEK);
        softAssert.assertEquals(title(), titleForWorkoutReportPage);
        softAssert.assertAll();
    }
    @Test(testName = "Оставить комментарий для актиности", dataProvider = "weekDates",description = "Открывается редактируемое окно     Workout Comment")
    public void checkReportComment(LocalDate startDate, LocalDate endDate) {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        workoutReportPage.openWorkoutReportPage();
        isTextDisplayed(WORKOUT_REPORT_MESSAGE_FOR_NO_FILTER);
        workoutReportPage.isPageOpened()
                .setStartDate(startDate)
                .setEndDate(endDate)
                .clickViewReportbutton()
                .vefifyTrainigblockExistence()
                .clickCommentIcon()
                .verifyCommentBlock()
                .fillComment(WORKOUT_REPORT_MESSAGE_FOR_NO_FILTER);
        verifyUrl(urlWorkoutReport);
        isTextNotDisplayed(WORKOUT_REPORT_MESSAGE_FOR_NO_FILTER);
        isTextDisplayed(WORKOUT_REPORT_HEADER_FOR_GROUP_BY_WEEK);
        softAssert.assertEquals(title(), titleForWorkoutReportPage);
        softAssert.assertAll();
    }

}
