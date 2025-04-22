package ru.home.vadim.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.home.vadim.webdriver.WebDriverSingleton;

import java.time.Duration;

public abstract class AbstractPage {
    protected WebDriver driver = WebDriverSingleton.getInstance().getDriver();
    protected WebDriverWait wait;

    protected AbstractPage() {
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
}
