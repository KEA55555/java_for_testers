package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import java.nio.file.Paths;

public class HelperBase {

    protected final ApplicationManager manager;

    public HelperBase(ApplicationManager manager) {
        this.manager = manager;
    }

    protected boolean isElementPresent(By locator) {
        try {
            manager.driver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    protected void click(By locator) {
        manager.driver.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        if (text != null) {
            click(locator);
            manager.driver.findElement(locator).clear();
            manager.driver.findElement(locator).sendKeys(text);
        }
    }

        protected void attach(By locator, String file) {
                manager.driver.findElement(locator).sendKeys(Paths.get(file).toAbsolutePath().toString());
            }
    }