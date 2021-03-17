package ru.geekbrains.qa.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.geekbrains.qa.base.BaseView;
import ru.geekbrains.qa.base.UserMenuPage;
import ru.geekbrains.qa.enums.NavigationBarTabs;

public class LoginPage extends BaseView {

    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver ;

    @FindBy(xpath = "//*[@id='drop-login']")
    private WebElement enterForm;

    @FindBy(xpath = "//*[@id='usrlog2']")
    private WebElement inputLogin;

    @FindBy(xpath = "//*[@id='usrpass2']")
    private WebElement inputPassword;

    @FindBy(xpath = "//*[@id='signin_btn']")
    private WebElement buttonSignIn;

    @FindBy(className = "isUser main_page_show_")
    private WebElement userMainPage;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage clickEnterButton(){
        enterForm.click();
        return new LoginPage(driver);
    }
    public LoginPage enterLogin(String login){
        inputLogin.sendKeys(login);
        return this;
    }

    public LoginPage enterPassword(String password){
        inputPassword.sendKeys(password);
        return this;
    }

    public HomePage clickLoginButton(){
        buttonSignIn.click();
        waitFiveSeconds.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id='body']/div[2]/div/ul[2]"))));
        return new HomePage(driver);
    }

    // Для использования в других тестах
    public HomePage authoriseWithCookie(String userLoginCookieName, String userLoginCookieValue, String userPassCookieName, String userPassCookieValue) {
        System.out.println("\nПодставляем в куки пользователя"); // Для контроля этапов выполнения теста
        driver.manage().addCookie(new Cookie(userLoginCookieName, userLoginCookieValue));
        driver.manage().addCookie(new Cookie(userPassCookieName, userPassCookieValue));
        System.out.println("Обновляем страницу");  // Для контроля этапов выполнения теста
        jsExecutor.executeScript("window.location.reload();");
        waitFiveSeconds.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id='body']/div[2]/div/ul[2]"))));

        return new HomePage(driver);
    }


}
