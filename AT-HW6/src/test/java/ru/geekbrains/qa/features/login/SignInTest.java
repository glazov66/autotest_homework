package ru.geekbrains.qa.features.login;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import ru.geekbrains.qa.base.OpenHomePageTest;
import ru.geekbrains.qa.pages.LoginPage;

import static ru.geekbrains.qa.commonData.ConfigurationData.USER_LOGIN;
import static ru.geekbrains.qa.commonData.ConfigurationData.USER_PASSWORD;

public class SignInTest extends OpenHomePageTest {

    @DisplayName("Тест авторизации зарегистрированного пользователя")
    @Test
    public void loginRegisteredUserTest() {
        System.out.println("\nЗапуск loginRegisteredUserTest, страница: " + driver.getTitle());
        new LoginPage(driver)
                .clickEnterButton()
                .enterLogin(USER_LOGIN)
                .enterPassword(USER_PASSWORD)
                .clickLoginButton();

        Assertions.assertTrue(driver.findElement(By.xpath("//*[@id='body']/div[2]/div/ul[2]")).isDisplayed());

    }
}
