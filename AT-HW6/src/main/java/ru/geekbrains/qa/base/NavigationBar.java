package ru.geekbrains.qa.base;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import ru.geekbrains.qa.enums.NavigationBarTabs;
import ru.geekbrains.qa.base.BaseView;
import ru.geekbrains.qa.base.UserMenuPage;
import ru.geekbrains.qa.pages.MailSectionViewPage;
import ru.geekbrains.qa.pages.NewPostPage;
import ru.geekbrains.qa.pages.SearchSettingsSectionPage;


public class NavigationBar extends BaseView {


    public NavigationBar(WebDriver driver) {
        super(driver);
    }

    public BaseView  moveCursorToNavigationTab(NavigationBarTabs tab) {
        Actions actions = new Actions(driver);
        actions
                .moveToElement(driver.findElement(tab.getBy()))
                .build()
                .perform();
        switch (tab) {
            case UMAIL:
                return new MailSectionViewPage(driver);

            case NEW_POST:
                return new NewPostPage(driver);

            case SETTINGS:
                return new SearchSettingsSectionPage(driver);

            default:
                throw new IllegalArgumentException("Another tabs currently not implemented");
        }
    }


    public NavigationBar checkTabVisibility(NavigationBarTabs tabs) {
        Assertions.assertTrue(driver.findElement(tabs.getBy()).isDisplayed());
        return this;
    }
}
