package ru.geekbrains.qa;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
//важный импорт, указать, что в импорте должен быть именно он
import org.junit.jupiter.api.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HW5_DiaryTests {

    public static final String START_PAGE_URL = "https://diary.ru/";
    public static final String USER_LOGIN = "chapeau_claque";
    public static final String USER_PASSWORD = "#Password";
    public static final String UMAIL_SECTION_TITLE = "U-mail";

    static final Logger log = LoggerFactory.getLogger(HW5_DiaryTests.class);

    // метод выполняется перед каждым тестом (демонстрационный, для контроля)
    @BeforeEach
    void setUpTest() {
        log.info("\t\tBEFORE CURRENT TEST");
    }

    // метод выполняется после каждого теста (демонстрационный, для контроля)
    @AfterEach
    void tearDownTest() {
        log.info("\t\tAFTER CURRENT TEST");
    }

    @DisplayName("Тест авторизации на сайте")
    @Test
    void DiarySignInTest() throws InterruptedException {

        WebDriverManager.chromedriver().setup();

        WebDriver driver = getWebDriver();

        login(driver);

        // вывести в консоль для контроля имя авторизовавшегося пользователя - "chapeau claque"
        WebElement userName = driver.findElement(By.xpath("//*[@id=\"body\"]/div[2]/div/ul[2]/li[9]/a"));
        System.out.println("\nКонтроль userName: " + userName.getText() + "\n");

        Assertions.assertEquals(USER_LOGIN, userName.getText());

        driver.quit();

    }

    @DisplayName("Тест перехода на закладку 'Mail'")
    @Test
    void DiaryMailTest() throws InterruptedException {

        WebDriverManager.chromedriver().setup();

        WebDriver driver = getWebDriver();

        login(driver);

        // нажать кнопку "U-Mail"
        WebElement uMailButton = driver.findElement(By.xpath("//*[@id=\"body\"]/div[2]/div/ul[2]/li[6]"));
        uMailButton.click();

        // Ожидание открытия раздела "U-mail" 2 сек
        getWaitTwoSecond(driver).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id=\"page_content\"]/h1"))));

        // вывести в консоль название открывшегося раздела - "U-mail"
        WebElement uMailSectionTitle = driver.findElement(By.xpath("//*[@id=\"page_content\"]/h1"));
        System.out.println("\nКонтроль uMailSectionTitle: " + uMailSectionTitle.getText() + "\n");

        Assertions.assertEquals(UMAIL_SECTION_TITLE, uMailSectionTitle.getText());

        driver.quit();

    }

    public WebDriver getWebDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");

        WebDriver driver = new ChromeDriver(options);

        driver.get(START_PAGE_URL);
        driver.manage().window().setSize(new Dimension(1125, 804));
        driver.manage().window().setPosition(new Point(600, 20));
        return driver;
    }


    void login(WebDriver driver) throws InterruptedException {
        // нажать кнопку "Вход"
        WebElement signInForm = driver.findElement(By.xpath("//*[@id=\"drop-login\"]"));
        signInForm.click();

        // Ожидание открытия формы авторизации 2 сек
        getWaitTwoSecond(driver).until(ExpectedConditions.visibilityOf(driver.findElement(By.id("loginform"))));

        // поставить курсор в поле "Логин"
        WebElement signInField = driver.findElement(By.xpath("//*[@id=\"loginform\"]/form/div[1]"));
        signInField.click();

        //ввести логин в поле "Логин"
        WebElement signInTextInput = driver.findElement(By.xpath("//*[@id='usrlog2']"));
        signInTextInput.sendKeys(USER_LOGIN);

        // поставить курсор в поле "Пароль"
        WebElement passwordField = driver.findElement(By.xpath("//*[@id=\"loginform\"]/form/div[2]"));
        passwordField.click();

        //ввести пароль в поле "Пароль"
        WebElement passwordTextInput = driver.findElement(By.xpath("//*[@id=\"usrpass2\"]"));
        passwordTextInput.sendKeys(USER_PASSWORD);

        // нажать кнопку "Войти"
        WebElement signInButton = driver.findElement(By.xpath("//*[@id=\"signin_btn\"]"));
        signInButton.click();

    }


    WebDriverWait getWaitTwoSecond(WebDriver driver) {
        WebDriverWait waitTwoSecond = new WebDriverWait(driver, 2);
        return waitTwoSecond;
    }

}
