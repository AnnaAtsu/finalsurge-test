package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Selenide.title;
import static constants.ConstantElements.email;
import static constants.ConstantElements.password;
import static elements.Elements.titleForDashboardPage;
import static urls.Urls.urlCalendar;

@Log4j2
public class CalendarTest extends BaseTest {


    @Test(testName = "Отображение календаря на текущий месяц", description = "Календарь показывает месяц и год")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Calendar component")
    public void checkCalendarActualMonthExistence() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        urlAssertion.verifyUrl(urlCalendar);
        softAssert.assertEquals(title(), titleForDashboardPage);
        calendarPage.verifyCurrentMonthAndYear();
        softAssert.assertAll();
    }

    @Test(testName = "Отображение календаря на текущий месяц", description = "Календарь показывается, дни корректно распределены по сетке")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Calendar component")
    public void checkCalendarExistence() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        urlAssertion.verifyUrl(urlCalendar);
        softAssert.assertEquals(title(), titleForDashboardPage);
        softAssert.assertAll();
        calendarPage.verifyCalendarBlock();
    }

    @Test(testName = "Навигация по месяцам (← →) вправо  ", description = "При нажатии стрелки месяц корректно переключается вперед")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Calendar component")
    public void checkCalendarMonthNavigationToTheRight() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        urlAssertion.verifyUrl(urlCalendar);
        softAssert.assertEquals(title(), titleForDashboardPage);
        softAssert.assertAll();
        calendarPage.goToNextMonth()
                .goToNextMonthAndWaitForUpdate();

    }

    @Test(testName = "Навигация по месяцам (← →) влево ", description = "При нажатии стрелки месяц корректно переключается назад ")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Calendar component")
    public void checkCalendarMonthNavigationToTheLeft() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        urlAssertion.verifyUrl(urlCalendar);
        softAssert.assertEquals(title(), titleForDashboardPage);
        softAssert.assertAll();
        calendarPage.goToPrevMonth()
                .goToPrevMonthAndWaitForUpdate();

    }

    @Test(testName = "Клик по пустому дню ", description = "Появляется форма быстрого добавления тренировки на выбранную дату")
    @Severity(SeverityLevel.MINOR)
    @Feature("Calendar component")
    public void checkEmptyCalendarCell() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        urlAssertion.verifyUrl(urlCalendar);
        softAssert.assertEquals(title(), titleForDashboardPage);
        softAssert.assertAll();
        calendarPage.checkCellWithoutWorkout();
    }

    @Test(testName = "Клик по дню с тренировкой  ", description = "Открывается модальное окно или страница с деталями тренировки")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Calendar component")
    public void checkFullCalendarCell() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        urlAssertion.verifyUrl(urlCalendar);
        softAssert.assertEquals(title(), titleForDashboardPage);
        softAssert.assertAll();
        calendarPage.checkCellWithtWorkout();
    }

    @Test(testName = "Отображение запланированных тренировок", description = "В ячейках дней с тренировками отображается  метка типа активности")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Calendar component")
    public void checkActivityLabelExistence() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        urlAssertion.verifyUrl(urlCalendar);
        softAssert.assertEquals(title(), titleForDashboardPage);
        softAssert.assertAll();
        calendarPage.checkActivityLabelExistence();
    }

    @Test(testName = "Цветовая маркировка типов тренировок", description = "Разные типы активности (Run, Bike, Swim, Custom) отображаются разными цветами")
    @Severity(SeverityLevel.MINOR)
    @Feature("Calendar component")
    public void checkActivityColors() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        urlAssertion.verifyUrl(urlCalendar);
        softAssert.assertEquals(title(), titleForDashboardPage);
        softAssert.assertAll();
        calendarPage.checkColorInWorkout("Bike", "#4dbd53")
                .checkColorInWorkout("Swim", "#4dabf7")
                .checkColorInWorkout("Run", "#8c8c8c");
    }
}
