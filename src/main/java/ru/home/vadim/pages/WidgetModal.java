package ru.home.vadim.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WidgetModal {
    private WebElement modalRoot;

    public boolean modalIsVisible() {
        return modalRoot.isDisplayed();
    }
}
