package tests;

import manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;

public class TestBase {

    protected static ApplicationManager app;

    protected void openHomePage() {
        ApplicationManager.driver.findElement(By.linkText("home")).click();
    }

    @BeforeEach
    public void setUp() {
        if (app == null) {
            app = new ApplicationManager();
        }
        app.init();

    }
}