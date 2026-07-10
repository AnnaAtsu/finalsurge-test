package tests;

import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


import static com.codeborne.selenide.Selenide.title;
import static constants.ConstantElements.email;
import static constants.ConstantElements.password;
import static elements.Elements.*;

public class UserProfileTest extends BaseTest{


    private static final Faker faker = new Faker();

    @DataProvider(name = "validUserData")
    public Object[][] validUserData() {
        return new Object[][] {
                {
                        faker.name().firstName(),
                        faker.name().lastName(),
                }
        };
    }


    @Test(testName = "Просмотр профиля ",  description = "Отображаются: имя, email, фото профиля")
    public void checkUserPrifilePage() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        userProfilePage.openPage()
                        .isPageOpened()
                                .verifyUserProfilePage();
        softAssert.assertEquals(title(), titleForUserProfilePage);
        verifyUrl(urlUserProfile);
        isTextDisplayed("User Profile");
        softAssert.assertAll();
    }



    @Test(testName = "Редактирование имени и фамилии", dataProvider = "validUserData", description = "После сохранения новое имя отображается в профиле и шапке сайта")
    public void checkEditNameSurname(String firstName, String lastName) {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage();
        loginPage.enterCreds(email, password);
        loginPage.pushLoginButton();
        userProfilePage.openPage()
                .isPageOpened()
                .clickEditButton()
                .editName(firstName, lastName);
        //доделать
        softAssert.assertAll();
    }
}
