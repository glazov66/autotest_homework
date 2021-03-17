package ru.geekbrains.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.geekbrains.qa.base.BaseView;

public class MailSectionViewPage extends BaseView {

    public MailSectionViewPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='page_content']/h1")
    private WebElement uMailSectionTitle;

    @FindBy(xpath = "//*[@id=\"page_content\"]/div/p/a")
    private WebElement newLetterButton;

    public MailSectionViewPage printSectionTitle(){
        System.out.println("uMailSectionTitle со страницы MailSectionViewPage: " + uMailSectionTitle.getText());
//        uMailSectionTitle.getText();
        return this;
    }

    public MailSectionViewPage writeNewLetter(){
                newLetterButton.click();
                return this;
    }


}
