package ru.geekbrains.qa.features.login;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import ru.geekbrains.qa.base.OpenHomePageTest;
import ru.geekbrains.qa.pages.HomePage;

import static ru.geekbrains.qa.commonData.ConfigurationData.HOME_PAGE_URL;

public class HomePageTest extends OpenHomePageTest {

    @DisplayName("Тест открытия домашней страницы")
    @Test
    public void homePageTest() {
        System.out.println(driver.getTitle());
        new HomePage(driver)
                .checkUrl(HOME_PAGE_URL);

    }
}
