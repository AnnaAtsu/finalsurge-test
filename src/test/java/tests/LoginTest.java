package tests;

import org.testng.annotations.Test;

public class LoginTest extends BaseTest{

     @Test(description = "Успешная авторизация пользователя")
    public void checkLogin() {
         loginPage.openPage();
     }
}
