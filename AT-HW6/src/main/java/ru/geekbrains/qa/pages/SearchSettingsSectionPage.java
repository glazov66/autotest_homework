package ru.geekbrains.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.geekbrains.qa.base.BaseView;

import java.lang.reflect.Array;

public class SearchSettingsSectionPage extends BaseView {

    public SearchSettingsSectionPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id=\"query\"]")
    private WebElement searchSettingsField;

    // поставить курсор в поле поиска и ввести тестовый текст "Профиль"
    public SearchSettingsSectionPage enterSearchText (String searchKey){
        searchSettingsField.click();
        searchSettingsField.sendKeys(searchKey);
        return this;
    }
    public SearchSettingsSectionPage waitSearchResultPopUp(){

        waitFiveSeconds.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id='ss_my_profile']/div"))));
        return this;
    }

}
