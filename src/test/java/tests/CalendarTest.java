package tests;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Selenide.title;
import static elements.Elements.*;
import static elements.Elements.titleForDashboardPage;

@Log4j2
public class CalendarTest extends BaseTest{


    @Test(testName = "Отображение календаря на текущий месяц", description = "Календарь показывает месяц и год")
    public void checkCalendarActualMonthExistence() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        verifyUrl(urlCalendar);
        softAssert.assertEquals(title(), titleForDashboardPage);
        calendarPage.verifyCurrentMonthAndYear();
        softAssert.assertAll();
    }

    @Test(testName = "Отображение календаря на текущий месяц", description = "Календарь показывается, дни корректно распределены по сетке")
    public void checkCalendarExistence() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        verifyUrl(urlCalendar);
        softAssert.assertEquals(title(), titleForDashboardPage);
        softAssert.assertAll();
        calendarPage.verifyCalendarBlock();
    }

    @Test(testName = "Навигация по месяцам (← →) вправо  ", description = "При нажатии стрелки месяц корректно переключается вперед")
    public void checkCalendarMonthNavigationToTheRight() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        verifyUrl(urlCalendar);
        softAssert.assertEquals(title(), titleForDashboardPage);
        softAssert.assertAll();
        calendarPage.goToNextMonth()
                    .goToNextMonthAndWaitForUpdate();

    }

    @Test(testName = "Навигация по месяцам (← →) влево ", description = "При нажатии стрелки месяц корректно переключается назад ")
    public void checkCalendarMonthNavigationToTheLeft() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        verifyUrl(urlCalendar);
        softAssert.assertEquals(title(), titleForDashboardPage);
        softAssert.assertAll();
        calendarPage.goToPrevMonth()
                    .goToPrevMonthAndWaitForUpdate();

    }

    @Test(testName = "Клик по пустому дню ", description = "Появляется форма быстрого добавления тренировки на выбранную дату")
    public void checkEmptyCalendarCell() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        verifyUrl(urlCalendar);
        softAssert.assertEquals(title(), titleForDashboardPage);
        softAssert.assertAll();
        calendarPage.checkCellWithoutWorkout();
    }

    @Test(testName = "Клик по дню с тренировкой  ", description = "Открывается модальное окно или страница с деталями тренировки")
    public void checkFullCalendarCell() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        verifyUrl(urlCalendar);
        softAssert.assertEquals(title(), titleForDashboardPage);
        softAssert.assertAll();
        calendarPage.checkCellWithtWorkout();
    }

    @Test(testName = "Отображение запланированных тренировок", description = "В ячейках дней с тренировками отображается  метка типа активности")
    public void checkActivityLabelExistence() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        verifyUrl(urlCalendar);
        softAssert.assertEquals(title(), titleForDashboardPage);
        softAssert.assertAll();
        calendarPage.checkActivityLabelExistence();
    }

    @Test(testName = "Цветовая маркировка типов тренировок", description = "Разные типы активности (Run, Bike, Swim, Custom) отображаются разными цветами")
    public void checkActivityColors() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        verifyUrl(urlCalendar);
        softAssert.assertEquals(title(), titleForDashboardPage);
        softAssert.assertAll();
        calendarPage.checkColorInWorkout("Bike", "#4dbd53")
                    .checkColorInWorkout("Swim", "#4dabf7")
                    .checkColorInWorkout("Run", "#8c8c8c");
    }
}
