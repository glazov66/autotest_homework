package ru.geekbrains.qa;

import org.junit.jupiter.api.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import static ru.geekbrains.qa.HW3_DiaryMail.getWaitTwoSecond;
import static ru.geekbrains.qa.HW3_DiaryMail.login;
import static ru.geekbrains.qa.HW3_DiarySignIn.SIGN_IN_PAGE_URL;

public class HW5_LoginnedUserTests {

    public static final String MY_DIARY_NAME = "Diary-1";
    public static final String POST_TEXT = "Текст сообщения";
    public static final String SEARCH_SETTINGS_TEXT = "профиль";
    private WebDriver webDriver;


    static final Logger log = LoggerFactory.getLogger(HW5_LoginnedUserTests.class);

    @BeforeAll
    static void startWebDriverManager() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void authorisation() throws InterruptedException {
        getWebDriver();
        login(webDriver);
    }

    private WebDriver getWebDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");

        webDriver = new ChromeDriver(options);

        webDriver.get(SIGN_IN_PAGE_URL);
        webDriver.manage().window().setSize(new Dimension(1125, 804));
        webDriver.manage().window().setPosition(new Point(600, 20));
        return webDriver;
    }

    @DisplayName("Тест черновика нового сообщения")
    @Test

    public void NewPostTest() throws InterruptedException {

        // нажать кнопку "Мой дневник"
        WebElement myDiaryButton = webDriver.findElement(By.xpath("//*[@id=\"body\"]/div[2]/div/ul[2]/li[1]/a"));
        myDiaryButton.click();
        getWaitTwoSecond(webDriver).until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath("//*[@id=\"epigraph\"]/p"))));

        // нажать кнопку "Новая запись"
        WebElement newPost = webDriver.findElement(By.xpath("//*[@id=\"Journal\"]/div[2]/div/ul[2]/li[2]"));
        newPost.click();

        // Ожидание открытия раздела "Сообщение" 2 сек
        getWaitTwoSecond(webDriver).until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath("//*[@id=\"msg_form\"]/div[1]"))));

        // поставить курсор в поле "Сообщение" и ввести тестовый текст
        WebElement newPostField = webDriver.findElement(By.xpath("//*[@id=\"message\"]"));
        newPostField.click();
        newPostField.sendKeys(POST_TEXT);

        // нажать кнопку "Предпросмотр"
        WebElement previewButton = webDriver.findElement(By.xpath("//*[@id=\"preview\"]"));
        previewButton.click();

        // поставить галку в чек-бокс "Отключить автосохранение"
        WebElement dontAutoSave = webDriver.findElement(By.xpath("//*[@id=\"DisableAutoSave\"]/label"));
        dontAutoSave.click();

        // переключиться на второе окно
        ArrayList<String> windows = new ArrayList(webDriver.getWindowHandles());
        webDriver.switchTo().window(windows.get(1));

        // проверить текст в поле сообщения
        WebElement previewPostText = webDriver.findElement(By.xpath("//*[@id=\"post1\"]/div[1]/div[2]/div[2]"));
        Assertions.assertEquals(POST_TEXT, previewPostText.getText());
        System.out.println("\nКонтроль текста сообщения: " + previewPostText.getText() + "\n");

        webDriver.quit();
    }

    @DisplayName("Тест поиска раздела в Настройках")
    @Test

    public void SearchSettingsTest() throws InterruptedException {

        // нажать кнопку "Настройки"
        WebElement settingsButton = webDriver.findElement(By.xpath("//*[@id=\"body\"]/div[2]/div/ul[2]/li[7]/a"));
        settingsButton.click();
        getWaitTwoSecond(webDriver).until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath("//*[@id=\"page_content\"]/div[1]/p[1]"))));

        // поставить курсор в поле поиска и ввести тестовый текст "Профиль"
        WebElement searchSettingsField = webDriver.findElement(By.xpath("//*[@id=\"query\"]"));
        searchSettingsField.click();
        searchSettingsField.sendKeys(SEARCH_SETTINGS_TEXT);

        WebElement searchResponseField = webDriver.findElement(By.xpath("//*[@id=\"ss_my_profile\"]/div"));
        Thread.sleep(1000);

        Assertions.assertTrue(searchResponseField.isDisplayed());

        webDriver.quit();

    }
}
