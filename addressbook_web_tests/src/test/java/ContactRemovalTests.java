import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ContactRemovalTests {
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
  public void contactRemovalTests() {
    driver.get("http://localhost/addressbook/");
    driver.manage().window().setSize(new Dimension(1296, 816));
    driver.findElement(By.id("2")).click();
    driver.findElement(By.cssSelector(".left:nth-child(8) > input")).click();
    driver.findElement(By.linkText("home")).click();
    driver.findElement(By.linkText("Logout")).click();
  }
}
