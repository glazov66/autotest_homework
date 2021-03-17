package ru.geekbrains.qa.features.settings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.geekbrains.qa.base.OpenHomePageTest;
import ru.geekbrains.qa.commonData.ConfigurationData;
import ru.geekbrains.qa.enums.NavigationBarTabs;
import ru.geekbrains.qa.pages.LoginPage;
import ru.geekbrains.qa.pages.SearchSettingsSectionPage;


public class SearchSectionTest extends OpenHomePageTest {

    @DisplayName("Тест строки поиска в настройках")
    @Test
    public void searchSettingsSection() throws InterruptedException {

        SearchSettingsSectionPage searchSectionTest = (SearchSettingsSectionPage) new LoginPage(driver)
                .authoriseWithCookie(ConfigurationData.USER_LOGIN_COOKIE_NAME, ConfigurationData.USER_LOGIN, ConfigurationData.USER_PASS_COOKIE_NAME, ConfigurationData.USER_PASS_COOKIE_VALUE)
                .getNavigationBar()
                .moveCursorToNavigationTab(NavigationBarTabs.SETTINGS);

        // нажать кнопку "Настройки"
        WebElement settingsButton = driver.findElement(By.xpath("//*[@id='body']/div[2]/div/ul[2]/li[7]/a"));
        settingsButton.click();

        searchSectionTest
                .enterSearchText(ConfigurationData.SEARCH_SETTINGS_TEXT)
                .waitSearchResultPopUp();

        WebElement searchResponseField = driver.findElement(By.xpath("//*[@id='ss_my_profile']/div"));

        Assertions.assertTrue(searchResponseField.isDisplayed());


    }

}
