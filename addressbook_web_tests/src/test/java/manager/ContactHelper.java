package manager;


import org.openqa.selenium.By;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void openHomePage() {
        click(By.linkText("home"));
    }

    public void createContact() {
        click(By.linkText("add new"));
        click(By.name("firstname"));
        type(By.name("firstname"), "name");
        click(By.name("lastname"));
        type(By.name("lastname"), "name2");
        click(By.name("address"));
        type(By.name("address"), "address");
        click(By.name("home"));
        type(By.name("home"), "8888");
        click(By.name("email"));
        type(By.name("email"), "mail@mail.ru");
        click(By.name("theform"));
        click(By.cssSelector("input:nth-child(75)"));
        click(By.linkText("home page"));
    }

    public void checkButtonEdit() {
        if (!isElementPresent((By.cssSelector("tr:nth-child(2) > .center:nth-child(8) img")))) {
            createContact();
        }
    }

    public void removeContact() {
        click(By.linkText("home"));
        click(By.cssSelector("tr:nth-child(2) > .center:nth-child(8) img"));
        click(By.cssSelector("input:nth-child(2)"));
    }
}
