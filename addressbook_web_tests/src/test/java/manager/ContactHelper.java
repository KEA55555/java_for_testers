package manager;


import org.openqa.selenium.By;

public class ContactHelper {

        private final ApplicationManager manager;

        public ContactHelper(ApplicationManager manager) {
            this.manager = manager;
        }

    public void openHomePage() {
        manager.driver.findElement(By.linkText("home")).click();
    }

    public void createContact() {
        manager.driver.findElement(By.linkText("add new")).click();
        manager.driver.findElement(By.name("firstname")).click();
        manager.driver.findElement(By.name("firstname")).sendKeys("name");
        manager.driver.findElement(By.name("lastname")).click();
        manager.driver.findElement(By.name("lastname")).sendKeys("name2");
        manager.driver.findElement(By.name("address")).click();
        manager.driver.findElement(By.name("address")).sendKeys("address");
        manager.driver.findElement(By.name("home")).click();
        manager.driver.findElement(By.name("home")).sendKeys("8888");
        manager.driver.findElement(By.name("email")).click();
        manager.driver.findElement(By.name("email")).sendKeys("mail@mail.ru");
        manager.driver.findElement(By.name("theform")).click();
        manager.driver.findElement(By.cssSelector("input:nth-child(75)")).click();
        manager.driver.findElement(By.linkText("home page")).click();
    }

    public void checkButtonEdit() {
        if (!manager.isElementPresent((By.cssSelector("tr:nth-child(2) > .center:nth-child(8) img")))) {
            createContact();
        }
    }

    public void removeContact() {
        manager.driver.findElement(By.linkText("home")).click();
        manager.driver.findElement(By.cssSelector("tr:nth-child(2) > .center:nth-child(8) img")).click();
        manager.driver.findElement(By.cssSelector("input:nth-child(2)")).click();
    }
}