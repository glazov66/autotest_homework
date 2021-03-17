package ru.geekbrains.qa.enums;

import org.openqa.selenium.By;

public enum NavigationBarTabs {

    MY_DIARY("//*[@id=\"body\"]/div[2]/div/ul[2]/li[1]/a"),
    NEW_POST("//*[@id=\"body\"]/div[2]/div/ul[2]/li[1]/a"),
    UMAIL("//*[@id=\"body\"]/div[2]/div/ul[2]/li[6]"),
    SETTINGS("//*[@id=\"body\"]/div[2]/div/ul[2]/li[7]/a");


    private final By by;

    public By getBy() {
        return by;
    }

    NavigationBarTabs(String xpath) {
        this.by = By.xpath(xpath);
    }

    }
