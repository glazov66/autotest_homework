package ru.geekbrains.qa.features.newPost;

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
import ru.geekbrains.qa.helpers.ScreenshotMaker;
import ru.geekbrains.qa.pages.LoginPage;
import ru.geekbrains.qa.pages.NewPostPage;

@Feature("New post")
@Severity(SeverityLevel.CRITICAL)
public class NewPostDraftTest extends OpenHomePageTest {

    @Story("Черновик новой записи в дневник")
    @DisplayName("Тест новой записи в дневник")
    @Test
    public void newPostDraftTest() {
        NewPostPage newPostDraft = (NewPostPage) new LoginPage(driver)
                .clickEnterButton()
                .enterLogin(ConfigurationData.USER_LOGIN)
                .enterPassword(ConfigurationData.USER_PASSWORD)
                .clickLoginButton()
                .getNavigationBar()
                .moveCursorToNavigationTab(NavigationBarTabs.NEW_POST);

        // нажать кнопку "Мой дневник"
        WebElement myDiaryButton = driver.findElement(By.xpath("//*[@id='body']/div[2]/div/ul[2]/li[1]/a"));
        myDiaryButton.click();

        // нажать кнопку "Новая запись"
        WebElement newPost = driver.findElement(By.xpath("//*[@id='Journal']/div[2]/div/ul[2]/li[2]"));
        newPost.click();

        newPostDraft
                .newPostEnter(ConfigurationData.POST_TEXT)
                .turnOffaAutoSave()
                .clickPreviewButton()
                .switchToSecondWindow();

        ScreenshotMaker.makeScreenshot(driver,"newPost.jpg");

        // проверить текст в поле сообщения
        WebElement previewPostText = driver.findElement(By.xpath("//*[@id='post1']/div[1]/div[2]/div[2]"));
        Assertions.assertEquals(ConfigurationData.POST_TEXT, previewPostText.getText());
        System.out.println("\nКонтроль текста сообщения: " + previewPostText.getText() + "\n");

    }

}
