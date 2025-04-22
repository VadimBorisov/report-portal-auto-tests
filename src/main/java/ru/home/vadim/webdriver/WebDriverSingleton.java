package ru.home.vadim.webdriver;

import org.openqa.selenium.WebDriver;

public class WebDriverSingleton {
    private static final ThreadLocal<WebDriverSingleton> instance = new ThreadLocal<>();
    private WebDriver driver;

    private WebDriverSingleton() {
        driver = WebDriverFactory.getWebDriver();
    }

    public static synchronized WebDriverSingleton getInstance() {
        if (instance.get() == null) {
            instance.set(new WebDriverSingleton());
        }

        return instance.get();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void close() {
        try {
            driver.quit();
            driver = null;
        } finally {
            instance.remove();
        }
    }
}
