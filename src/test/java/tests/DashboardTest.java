package tests;

import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Selenide.title;
import static elements.Elements.*;

@Log4j2
public class DashboardTest extends BaseTest {

    @Test(testName = "Успешный переход на страницу дашборда", description = "Успешный переход на страницу дашборда")
    public void openDashboardPage() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        dashboardPage.isPageOpened();
        verifyUrl(urlCalendar);
        softAssert.assertEquals(title(), titleForDashboardPage);
        softAssert.assertAll();
   }

    @Test(testName = "Навигационное меню: все пункты кликабельны", description = "Каждый пункт меню ведёт на соответствующую страницу (Calendar, Workouts, Reports и т.д.)")
    public void checkClickNavMenu() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        dashboardPage.isPageOpened();
        dashboardPage.clickDefaultPage();
        verifyUrl(urlDefault);
        softAssert.assertEquals(title(), titleForDefaultPage);
        dashboardPage.openPage();
        dashboardPage.clickReportPage();
        verifyUrl(urlReport);
        softAssert.assertEquals(title(), titleForReportPage);
        dashboardPage.clickCalendarPage();
        verifyUrl(urlCalendar);
        dashboardPage.clickMailPage();
        verifyUrl(urlMail);
        softAssert.assertEquals(title(), titleForMailPage);
        softAssert.assertAll();
    }
    @Test(testName = "Отображение сводки за неделю (Weekly Summary)", description = "Виджет показывает неделю и уменьшенный календарь")
    public void checkWeeklySummary() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        dashboardPage.isPageOpened()
                 .clickWeekButton()
                .verifyWeekHeader();
        verifyUrl(urlCalendar);
        softAssert.assertEquals(title(), titleForDashboardPage);
        softAssert.assertAll();
    }

    @Test(testName = "Навигационное меню: кнопка Garmin", description = "Переход на страницу с ссылками на часы ")
    public void checkGarminButton() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        dashboardPage.isPageOpened();
        verifyUrl(urlCalendar);
        softAssert.assertEquals(title(), titleForDashboardPage);
        dashboardPage.clickGarminButton();
        verifyUrl(urlGarmin);
        softAssert.assertEquals(title(), titleForGarminPage);
        softAssert.assertAll();
    }

    @Test(testName = "Отображение сводки за 6 недель", description = "Виджет показывает неделю Week of... календаря нет")
    public void checkSixWeeksWidget() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        dashboardPage.isPageOpened();
        verifyUrl(urlCalendar);
        //доделать
    }
}
