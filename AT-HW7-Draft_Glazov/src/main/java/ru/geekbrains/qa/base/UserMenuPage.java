package ru.geekbrains.qa.base;

import org.openqa.selenium.WebDriver;
import ru.geekbrains.qa.enums.NavigationBarTabs;

public abstract class UserMenuPage extends BaseView {

    public UserMenuPage(WebDriver driver) {
        super(driver);
    }

    abstract public BaseView clickUserMenuButton(NavigationBarTabs buttons);
}
