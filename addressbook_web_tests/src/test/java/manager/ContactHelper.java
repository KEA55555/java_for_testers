package manager;


import model.ContactData;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void openHomePage() {
        click(By.linkText("home"));
    }

    public void createContact(ContactData contact) {
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

    public void initContactModification(int index) {
        click(By.cssSelector(String.format("tr:nth-child(%d) > .center:nth-child(8) img", index + 2)));
    }

    public void removeContact(ContactData contact) {
        openHomePage();
        selectContactById(String.valueOf(contact));
        removeSelectedContacts();
        click(By.cssSelector("input:nth-child(2)"));
    }

    private void selectContactById(String id) {
        click(By.cssSelector("input[value='" + id + "']"));
    }

    public int getCount() {
        openHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void removeAllContacts() {
        openHomePage();
        selectAllContacts();
        removeSelectedContacts();
    }

    private void removeSelectedContacts() {
        click(By.cssSelector(".left:nth-child(8) > input"));
    }

    private void selectAllContacts() {
        var checkboxes = manager.driver.findElements(By.id("MassCB"));
        for (var selectAll : checkboxes) {
            selectAll.click();
        }
    }

    public List<ContactData> getList() {
        openHomePage();
        var contacts = new ArrayList<ContactData>();
        var trs = manager.driver.findElements(By.cssSelector("tr[name='entry']"));

        for (var tr : trs) {
            var tds = tr.findElements(By.tagName("td"));

            if (tds.size() >= 3) {
                var checkbox = tds.get(0).findElement(By.name("selected[]"));
                var id = checkbox.getAttribute("value");
                var lastname = tds.get(1).getText();
                var firstname = tds.get(2).getText();
                contacts.add(new ContactData()
                        .withId(id)
                        .withFirstName(firstname)
                        .withLastName(lastname));
            }
        }
        return contacts;
    }

    public void modifyContact(ContactData modifiedContact, int index) {
        openHomePage();
        initContactModification(index);
        fillContactForm(modifiedContact);
        submitContactModification();
        returnToHomePage();
    }

    private void returnToHomePage() {
        click(By.linkText("home"));
    }

    private void submitContactModification() {
        click(By.name("update"));
    }

    private void fillContactForm(ContactData contact) {
        click(By.name("firstname"));
        type(By.name("firstname"), contact.firstname());
    }
}