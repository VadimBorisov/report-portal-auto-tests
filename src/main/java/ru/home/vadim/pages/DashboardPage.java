package ru.home.vadim.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DashboardPage extends AbstractPage {
    @FindBy(xpath = "//button/span[contains(text(), 'Add new widget')]")
    private WebElement addWidgetButton;
    @FindBy(xpath = "//div[@id='modal-root']//div[contains(@class, 'widgetTypeItem')]")
    private List<WebElement> widgetsTypes;
    @FindBy(xpath = "//button/span[contains(text(), 'Next step')]")
    private WebElement nextButton;
    @FindBy(xpath = "//span[contains(text(), 'DEMO_FILTER')]")
    private WebElement filterButton;
    @FindBy(xpath = "//input[contains(@placeholder, 'Enter widget name')]")
    private WebElement widgetNameField;
    @FindBy(xpath = "//button[contains(text(), 'Add')]")
    private WebElement addButton;

    @Step("Пройти этапы добавления виджета")
    public String addWidgetToDashboard() {
        String widgetName = addWidget().selectWidgetType().clickNextButton().clickFilterButton()
                .clickNextButton().getWidgetName();
        clickAddButton();

        return widgetName;
    }

    @Step("Проверка что виджет с именем {widgetName} существует")
    public boolean widgetIsPresent(String widgetName) {
        WebElement widget = driver.findElement(By.xpath("//div[contains(text(), '" + widgetName + "')]"));
        return widget.isDisplayed();
    }

    @Step("Нажать на кнопку Add new widget")
    private DashboardPage addWidget() {
        addWidgetButton.click();
        return this;
    }

    @Step("Выбор типа виджета")
    private DashboardPage selectWidgetType() {
        widgetsTypes.getFirst().click();
        return this;
    }

    @Step("Нажать кнопку next")
    private DashboardPage clickNextButton() {
        nextButton.click();
        return this;
    }

    @Step("Выбор фильтра")
    private DashboardPage clickFilterButton() {
        filterButton.click();
        return this;
    }

    @Step("Сохранить имя виджета")
    private String getWidgetName() {
        return widgetNameField.getDomAttribute("value");
    }

    @Step("Сохранить виджет")
    private void clickAddButton() {
        addButton.click();
    }
}
