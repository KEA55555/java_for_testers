package ru.stqa.mantis.manager;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;

public class ApplicationManager {

    private WebDriver driver;

    private String string;

    private Properties properties;

    public SessionHelper SessionHelper;

    private HttpSessionHelper httpSessionHelper;

    private JamesCliHelper jamesCliHelper;

    public void init(String browser, Properties properties) {
        this.string = browser;
        this.properties = properties;
    }

    public WebDriver driver() {
        if (driver == null) {
            if ("chrome".equals(string)) {
                driver = new ChromeDriver();
            } else if ("firefox".equals(string)) {
                driver = new FirefoxDriver();
            } else {
                throw new IllegalArgumentException(String.format("Unknown browser %s", string));
            }
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get(properties.getProperty("web.baseUrl"));
            driver.manage().window().setSize(new Dimension(1297, 817));
        }
        return driver;
    }

    public SessionHelper session() {
        if (SessionHelper == null) {
            SessionHelper = new SessionHelper(this);
        }
        return SessionHelper;
    }

    public HttpSessionHelper http() {
        if (httpSessionHelper == null) {
            httpSessionHelper = new HttpSessionHelper(this);
        }
        return httpSessionHelper;
    }

    public String property(String name) {
        return properties.getProperty(name);
    }

    public JamesCliHelper jamesCli() {
        if (jamesCliHelper == null) {
            jamesCliHelper = new JamesCliHelper(this);
        }
        return jamesCliHelper;
    }
}