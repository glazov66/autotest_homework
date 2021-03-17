package ru.geekbrains.qa.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BaseView {

    protected WebDriver driver;
    protected WebDriverWait waitFiveSeconds;

    public BaseView(WebDriver driver) {
        this.driver = driver;
        this.waitFiveSeconds = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }
}
