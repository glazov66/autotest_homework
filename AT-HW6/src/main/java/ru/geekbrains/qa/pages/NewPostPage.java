package ru.geekbrains.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.geekbrains.qa.base.BaseView;

import java.util.ArrayList;

public class NewPostPage extends BaseView {

    public NewPostPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='body']/div[2]/div/ul[2]/li[1]/a")
    private WebElement myDiaryButton;

    @FindBy(xpath = "//*[@id='Journal']/div[2]/div/ul[2]/li[2]")
    private WebElement newPostButton;

    @FindBy(xpath = "//*[@id='message']")
    private WebElement newPostField;

    @FindBy(xpath = "//*[@id='preview']")
    private WebElement previewButton;

    @FindBy(xpath = "//*[@id='DisableAutoSave']/label")
    private WebElement autoSaveCheckBox;

    @FindBy(xpath = "//*[@id='post1']/div[1]/div[2]/div[2]")
    private WebElement previewPostText;



    // поставить курсор в поле "Сообщение" и ввести тестовый текст
    public NewPostPage newPostEnter(String postText) {
        newPostField.click();
        newPostField.sendKeys(postText);
        return this;
    }

    // нажать кнопку "Предпросмотр"
    public NewPostPage clickPreviewButton() {
        previewButton.click();
        return this;
    }

    // поставить галку в чек-бокс "Отключить автосохранение"
    public NewPostPage turnOffaAutoSave() {
        autoSaveCheckBox.click();
        return this;
    }

    // переключиться на второе окно
    public NewPostPage switchToSecondWindow() {
        ArrayList<String> windows = new ArrayList(driver.getWindowHandles());
        driver.switchTo().window(windows.get(1));
        return this;
    }

}
