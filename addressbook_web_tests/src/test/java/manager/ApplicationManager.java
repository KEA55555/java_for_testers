package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ApplicationManager {

    protected WebDriver driver;

    private LoginHelper session;

    private GroupHelper groups;

    public void init() {
        if (driver == null) {
            driver = new ChromeDriver();
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get("http://localhost/addressbook/");
            driver.manage().window().setSize(new Dimension(1297, 817));
            session().login("admin", "secret");
        }
    }

    public LoginHelper session() {
        if (session == null) {
            session = new LoginHelper(this);
        }
        return session;
    }

    public GroupHelper groups(){
        if (groups == null) {
            groups = new GroupHelper(this);
        }
        return groups;
    }

    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator).click();
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    public void createContact() {
        driver.findElement(By.linkText("add new")).click();
        driver.findElement(By.name("firstname")).click();
        driver.findElement(By.name("firstname")).sendKeys("name");
        driver.findElement(By.name("lastname")).click();
        driver.findElement(By.name("lastname")).sendKeys("name2");
        driver.findElement(By.name("address")).click();
        driver.findElement(By.name("address")).sendKeys("address");
        driver.findElement(By.name("home")).click();
        driver.findElement(By.name("home")).sendKeys("8888");
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).sendKeys("mail@mail.ru");
        driver.findElement(By.name("theform")).click();
        driver.findElement(By.cssSelector("input:nth-child(75)")).click();
        driver.findElement(By.linkText("home page")).click();
    }

    public void checkButtonEdit() {
        if (!isElementPresent((By.cssSelector("tr:nth-child(2) > .center:nth-child(8) img")))) {
            createContact();
        }
    }

    public void removeContact() {
        driver.findElement(By.linkText("home")).click();
        driver.findElement(By.cssSelector("tr:nth-child(2) > .center:nth-child(8) img")).click();
        driver.findElement(By.cssSelector("input:nth-child(2)")).click();
    }

    public void openHomePage() {
        driver.findElement(By.linkText("home")).click();
    }
}