package ru.geekbrains.qa.features.login;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import ru.geekbrains.qa.base.OpenHomePageTest;
import ru.geekbrains.qa.pages.HomePage;

import static ru.geekbrains.qa.commonData.ConfigurationData.HOME_PAGE_URL;

@Feature("Login")
@Severity(SeverityLevel.BLOCKER)
public class HomePageTest extends OpenHomePageTest {

    @Story("Открытие домашней страницы")
    @DisplayName("Тест открытия домашней страницы")
    @Test
    public void homePageTest() {
        System.out.println(driver.getTitle());
        new HomePage(driver)
                .checkUrl(HOME_PAGE_URL);

    }
}
