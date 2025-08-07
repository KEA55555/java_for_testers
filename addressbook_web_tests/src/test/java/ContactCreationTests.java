import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ContactCreationTests {
  private WebDriver driver;

  @BeforeEach
  public void setUp() {
    driver = new ChromeDriver();
  }
  
  @AfterEach
  public void tearDown() {
    driver.quit();
  }

  @Test
  public void contactCreationTests() {
    driver.get("http://localhost/addressbook/");
    driver.manage().window().setSize(new Dimension(1296, 816));
    driver.findElement(By.name("user")).click();
    driver.findElement(By.name("user")).sendKeys("admin");
    driver.findElement(By.name("pass")).click();
    driver.findElement(By.name("pass")).sendKeys("secret");
    driver.findElement(By.cssSelector("input:nth-child(7)")).click();
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
    driver.findElement(By.linkText("Logout")).click();
  }
}
