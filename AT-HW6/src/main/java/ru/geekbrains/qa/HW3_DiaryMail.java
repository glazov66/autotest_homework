package ru.geekbrains.qa;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static ru.geekbrains.qa.HW3_DiarySignIn.*;

public class HW3_DiaryMail {

    private static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();

        driver = getWebDriver();

        login(driver);

        Thread.sleep(1500);

        // нажать кнопку "U-Mail"
        WebElement uMailButton = driver.findElement(By.xpath("//*[@id=\"body\"]/div[2]/div/ul[2]/li[6]"));
        uMailButton.click();
        Thread.sleep(1500);

        // вывести в консоль название открывшегося раздела меню - "U-Mail"
        WebElement uMailPageTitle = driver.findElement(By.xpath("//*[@id=\"page_content\"]/h1"));
        System.out.println("uMailPageTitle: " + uMailPageTitle.getText());

        Thread.sleep(3000);

        driver.quit();

    }

    static WebDriver getWebDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");

        driver = new ChromeDriver(options);

        driver.get(SIGN_IN_PAGE_URL);
        driver.manage().window().setSize(new Dimension(1125, 804));
        driver.manage().window().setPosition(new Point(600, 20));
        return driver;
    }

    public static void login(WebDriver driver) throws InterruptedException {
        // нажать кнопку "Вход"
        WebElement signInForm = driver.findElement(By.xpath("//*[@id=\"drop-login\"]"));
        signInForm.click();

        // Ожидание открытия формы авторизации 2 сек
        getWaitTwoSecond(driver).until(ExpectedConditions.visibilityOf(driver.findElement(By.id("loginform"))));

        // поставить курсор в поле "Логин"
        WebElement signInField = driver.findElement(By.xpath("//*[@id=\"loginform\"]/form/div[1]"));
        signInField.click();

        //ввести  логин
        WebElement signInTextInput = driver.findElement(By.xpath("//*[@id='usrlog2']"));
        signInTextInput.sendKeys(USER_LOGIN);

        // поставить курсор в поле "Пароль"
        WebElement passwordField = driver.findElement(By.xpath("//*[@id=\"loginform\"]/form/div[2]"));
        passwordField.click();

        //ввести пароль
        WebElement passwordTextInput = driver.findElement(By.xpath("//*[@id=\"usrpass2\"]"));
        passwordTextInput.sendKeys(USER_PASSWORD);

        // нажать кнопку "Войти"
        WebElement signInButton = driver.findElement(By.xpath("//*[@id=\"signin_btn\"]"));
        signInButton.click();
    }

    static WebDriverWait getWaitTwoSecond(WebDriver driver) {
        WebDriverWait waitTwoSecond = new WebDriverWait(driver, 2);
        return waitTwoSecond;
    }
}
