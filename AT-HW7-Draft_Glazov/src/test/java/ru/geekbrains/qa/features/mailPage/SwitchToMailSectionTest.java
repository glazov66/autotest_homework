package ru.geekbrains.qa.features.mailPage;

import com.google.inject.multibindings.StringMapKey;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.geekbrains.qa.base.OpenHomePageTest;
import ru.geekbrains.qa.commonData.ConfigurationData;
import ru.geekbrains.qa.enums.NavigationBarTabs;
import ru.geekbrains.qa.pages.LoginPage;
import ru.geekbrains.qa.pages.MailSectionViewPage;

import javax.security.auth.login.Configuration;

@Feature("U-mail page")
@Severity(SeverityLevel.CRITICAL)
public class SwitchToMailSectionTest extends OpenHomePageTest {

    @Story("Открытие нового письма")
    @DisplayName("Тест открытия нового письма")
    @Test
    public void switchToMailSectionTest() {

        MailSectionViewPage uMailSectionView = (MailSectionViewPage) new LoginPage(driver)
                .authoriseWithCookie(ConfigurationData.USER_LOGIN_COOKIE_NAME, ConfigurationData.USER_LOGIN, ConfigurationData.USER_PASS_COOKIE_NAME, ConfigurationData.USER_PASS_COOKIE_VALUE)
                .getNavigationBar()
                .moveCursorToNavigationTab(NavigationBarTabs.UMAIL);


        // нажать кнопку "U-Mail"
        WebElement uMailButton = driver.findElement(By.xpath("//*[@id='body']/div[2]/div/ul[2]/li[6]"));
        uMailButton.click();

        uMailSectionView
                .writeNewLetter();

        WebElement newLetterPageTitle = driver.findElement(By.xpath("//*[@id='page_content']/h1"));

        System.out.println("\n newLetterPageTitle: " + newLetterPageTitle.getText()); // вывод заголовка для контроля
        Assertions.assertEquals(ConfigurationData.NEW_LETTER_SECTION_TITLE, newLetterPageTitle.getText());

    }
}
