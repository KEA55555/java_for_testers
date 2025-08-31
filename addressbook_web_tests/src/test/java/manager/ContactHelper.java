package manager;


import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

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
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        returnToHomePage();
    }

    public void createContact(ContactData contact, GroupData group) {
        initContactCreation();
        fillContactForm(contact);
        selectGroup(group);
        submitContactCreation();
        returnToHomePage();
    }

    private void selectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }

    public void initContactCreation() {
        openHomePage();
        click(By.linkText("add new"));
    }

    public void submitContactCreation() {
        click(By.cssSelector("input:nth-child(75)"));
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
        type(By.name("firstname"), contact.firstname());
        type(By.name("lastname"), contact.lastname());
        attach(By.name("photo"), contact.photo());
    }
}