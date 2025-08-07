package manager;

import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ApplicationManager {

    protected static WebDriver driver;

    public void init() {
        if (driver == null) {
            driver = new ChromeDriver();
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get("http://localhost/addressbook/");
            driver.manage().window().setSize(new Dimension(1297, 817));
            driver.findElement(By.name("user")).sendKeys("admin");
            driver.findElement(By.name("pass")).click();
            driver.findElement(By.name("pass")).sendKeys("secret");
            driver.findElement(By.cssSelector("input:nth-child(7)")).click();
        }
    }

    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator).click();
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    public void createGroup(GroupData group) {
        driver.findElement(By.name("new")).click();
        driver.findElement(By.name("group_name")).click();
        driver.findElement(By.name("group_name")).sendKeys(group.name());
        driver.findElement(By.name("group_header")).click();
        driver.findElement(By.name("group_header")).sendKeys(group.header());
        driver.findElement(By.name("group_footer")).click();
        driver.findElement(By.name("group_footer")).click();
        driver.findElement(By.name("group_footer")).sendKeys(group.footer());
        driver.findElement(By.name("submit")).click();
        driver.findElement(By.linkText("group page")).click();
    }

    public void openGroupsPage() {
        if (!isElementPresent(By.name("new"))) {
            driver.findElement(By.linkText("groups")).click();
        }
    }

    public boolean isGroupPresent() {
        return isElementPresent(By.name("selected[]"));
    }

    public void removeGroup() {
        driver.findElement(By.name("selected[]")).click();
        driver.findElement(By.name("delete")).click();
        driver.findElement(By.cssSelector(".msgbox")).click();
        driver.findElement(By.linkText("group page")).click();
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
}