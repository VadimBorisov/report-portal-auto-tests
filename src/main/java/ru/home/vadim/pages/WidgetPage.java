package ru.home.vadim.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class WidgetPage extends AbstractPage {
    @FindBy(xpath = "//input[@type='radio']")
    private WebElement widgetTypes;


    @Step("Выбор типа виджета")
    public WidgetPage selectWidgetType() {
        widgetTypes.click();
        return this;
    }


}
