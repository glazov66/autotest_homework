package ru.geekbrains.qa.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.geekbrains.qa.base.BaseView;
import ru.geekbrains.qa.base.NavigationBar;
import ru.geekbrains.qa.enums.NavigationBarTabs;


public class HomePage extends BaseView {

    private NavigationBar navigationBar;

    public HomePage(WebDriver driver) {
        super(driver);
        this.navigationBar = new NavigationBar(driver);
    }


    public void checkUrl(String url) {
        System.out.println("Метод checkUrl из класса HomePage - driver.getCurrentUrl(): " + url);
        Assertions.assertEquals(driver.getCurrentUrl(), url);

    }

    public NavigationBar getNavigationBar() {
        return navigationBar;
    }

}

