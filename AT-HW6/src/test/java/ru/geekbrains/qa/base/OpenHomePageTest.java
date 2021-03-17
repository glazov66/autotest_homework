package ru.geekbrains.qa.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static ru.geekbrains.qa.commonData.ConfigurationData.HOME_PAGE_URL;

public abstract class OpenHomePageTest {

    protected WebDriver driver;

    @BeforeAll
    public static void setUp(){
        System.out.println("Запуск @BeforeAll");
        WebDriverManager.chromedriver().setup();

    }

    @BeforeEach
    public void createHomePage() {
        System.out.println("\nЗапуск @BeforeEach\n" + "Начало текущего теста");
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");

        driver = new ChromeDriver(options);
        driver.get(HOME_PAGE_URL);
        driver.manage().window().setSize(new Dimension(1125, 804));
        driver.manage().window().setPosition(new Point(700, 20));
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        System.out.println("URL из теста createHomePage = " + driver.getCurrentUrl()); //для контроля выполнения теста createHomePage

    }

    @AfterEach
    public void tearDown(){
        driver.quit();
        System.out.println("Конец текущего теста");
    }
}
