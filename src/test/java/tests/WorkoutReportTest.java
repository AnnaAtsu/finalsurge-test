package tests;


import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;

import static com.codeborne.selenide.Selenide.title;
import static constants.ConstantElements.email;
import static constants.ConstantElements.password;
import static elements.Elements.*;
import static urls.Urls.urlWorkoutReport;

public class WorkoutReportTest extends BaseTest{

    @DataProvider(name="defaultDates")
    public Object[][] defaultDates() {
        return new Object[][] {
                {LocalDate.of(2026,7,5),LocalDate.of(2026,7, 10)}
        };
    }

    @Test(testName = "Отображение сводки тренировок за период", dataProvider = "defaultDates", description = "Таблица с агрегированными данными: кол-во тренировок, общая дистанция, длительность")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("WorkoutReport component")
    public void checkReportForListView(LocalDate startDate, LocalDate endDate) {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        workoutReportPage.openWorkoutReportPage();
        softAssert.assertTrue(textAssertion.isTextDisplayed(WORKOUT_REPORT_MESSAGE_FOR_NO_FILTER));
        workoutReportPage.isPageOpened()
                .setStartDate(startDate)
                .setEndDate(endDate)
                .clickViewReportbutton()
                .vefifyTrainigblockExistence();
        urlAssertion.verifyUrl(urlWorkoutReport);
        textAssertion.isTextNotDisplayed(WORKOUT_REPORT_MESSAGE_FOR_NO_FILTER);
        softAssert.assertTrue(textAssertion.isTextDisplayed(WORKOUT_REPORT_HEADER_FOR_LIST_VIEW));
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
    @Severity(SeverityLevel.NORMAL)
    @Feature("WorkoutReport component")
    public void checkReportForWeek(LocalDate startDate, LocalDate endDate) {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        workoutReportPage.openWorkoutReportPage();
        softAssert.assertTrue(textAssertion.isTextDisplayed(WORKOUT_REPORT_MESSAGE_FOR_NO_FILTER));
        workoutReportPage.isPageOpened()
                .setStartDate(startDate)
                .setEndDate(endDate)
                .chooseGroupByWeekButton()
                .clickViewReportbutton()
                .vefifyTrainigblockExistence();
        urlAssertion.verifyUrl(urlWorkoutReport);
        textAssertion.isTextNotDisplayed(WORKOUT_REPORT_MESSAGE_FOR_NO_FILTER);
        softAssert.assertTrue(textAssertion.isTextDisplayed(WORKOUT_REPORT_HEADER_FOR_GROUP_BY_WEEK));
        softAssert.assertEquals(title(), titleForWorkoutReportPage);
        softAssert.assertAll();
    }

    @Test(testName = "Фильтрация отчетов по типу активности", dataProvider = "weekDates", description = "При выборе типа активности данные на графиках и в таблице фильтруются")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("WorkoutReport component")
    public void checkReportForActivity(LocalDate startDate, LocalDate endDate) {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        workoutReportPage.openWorkoutReportPage();
        urlAssertion.verifyUrl(urlWorkoutReport);
        softAssert.assertTrue(textAssertion.isTextDisplayed(WORKOUT_REPORT_MESSAGE_FOR_NO_FILTER));
        workoutReportPage.isPageOpened();
        workoutsPage.selectActivityType(WORKOUT_ACTIVITY_TYPE);
        workoutReportPage
                .setStartDate(startDate)
                .setEndDate(endDate)
                .clickViewReportbutton()
                .vefifyTrainigblockExistence();
        textAssertion.isTextNotDisplayed(WORKOUT_REPORT_MESSAGE_FOR_NO_FILTER);
        softAssert.assertTrue(textAssertion.isTextDisplayed(WORKOUT_REPORT_MESSAGE_FOR_NO_FILTER));
        softAssert.assertTrue(textAssertion.isTextDisplayed(WORKOUT_ACTIVITY_TYPE));
        softAssert.assertEquals(title(), titleForWorkoutReportPage);
        softAssert.assertAll();
    }

    @Test(testName = "Группировка активностей", dataProvider = "weekDates",description = "График показывает длительность тренировок по месяцам / неделям")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("WorkoutReport component")
    public void checkReportForActivityButton(LocalDate startDate, LocalDate endDate) {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        workoutReportPage.openWorkoutReportPage();
        textAssertion.isTextDisplayed(WORKOUT_REPORT_MESSAGE_FOR_NO_FILTER);
        workoutReportPage.isPageOpened()
                .setStartDate(startDate)
                .setEndDate(endDate)
                .chooseGroupByActivityButton()
                .clickViewReportbutton()
                .vefifyTrainigblockExistence()
                .verifyActivityHeader(WORKOUT_ACTIVITY_TYPE);
        urlAssertion.verifyUrl(urlWorkoutReport);
        textAssertion.isTextNotDisplayed(WORKOUT_REPORT_MESSAGE_FOR_NO_FILTER);
        softAssert.assertTrue(textAssertion.isTextDisplayed(WORKOUT_REPORT_HEADER_FOR_GROUP_BY_WEEK));
        softAssert.assertEquals(title(), titleForWorkoutReportPage);
        softAssert.assertAll();
    }

    @Test(testName = "Оставить комментарий для активности", dataProvider = "weekDates",description = "Открывается редактируемое окно     Workout Comment")
    @Severity(SeverityLevel.MINOR)
    @Feature("WorkoutReport component")
    public void checkReportComment(LocalDate startDate, LocalDate endDate) {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        workoutReportPage.openWorkoutReportPage();
        softAssert.assertTrue(textAssertion.isTextDisplayed(WORKOUT_REPORT_MESSAGE_FOR_NO_FILTER));
        workoutReportPage.isPageOpened()
                .setStartDate(startDate)
                .setEndDate(endDate)
                .clickViewReportbutton()
                .vefifyTrainigblockExistence()
                .clickCommentIcon()
                .fillComment(WORKOUT_REPORT_MESSAGE_FOR_NO_FILTER);
        urlAssertion.verifyUrl(urlWorkoutReport);
        textAssertion.isTextNotDisplayed(WORKOUT_REPORT_MESSAGE_FOR_NO_FILTER);
        softAssert.assertTrue(textAssertion.isTextDisplayed(WORKOUT_REPORT_HEADER_FOR_GROUP_BY_WEEK));
        softAssert.assertEquals(title(), titleForWorkoutReportPage);
        softAssert.assertAll();
    }
}
