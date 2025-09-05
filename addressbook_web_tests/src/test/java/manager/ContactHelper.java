package manager;


import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.openqa.selenium.By.id;

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
        selectContactById(String.valueOf(contact.id()));
        removeSelectedContacts();
    }

    private void selectContactById(String id) {
        var trs = manager.driver.findElements(By.cssSelector("tr[name='entry']"));
        for (var tr : trs) {
            var tds = tr.findElements(By.tagName("td"));
            if (tds.size() >= 3) {
                var checkbox = tds.get(0).findElement(By.name("selected[]"));
                var contactId = checkbox.getAttribute("value");
                if (id.equals(contactId)) {
                    checkbox.click();
                    break;
                }
            }
        }
    }

    public int getCount() {
        openHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void removeAllContacts() {
        openHomePage();
        selectAllContacts();
        removeSelectedContacts();
        returnToHomePage();
    }

    private void removeSelectedContacts() {
        click(By.cssSelector(".left:nth-child(8) > input"));
    }

    private void selectAllContacts() {
        var checkboxes = manager.driver.findElements(id("MassCB"));
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
                var address = tds.get(3).getText();
                var email = tds.get(4).getText();
                contacts.add(new ContactData()
                        .withId(id)
                        .withFirstName(firstname)
                        .withLastName(lastname)
                        .withAddress(address)
                        .withEmail(email));
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

    public void returnToHomePage() {
        click(By.linkText("home"));
    }

    private void submitContactModification() {
        click(By.name("update"));
    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstname());
        type(By.name("lastname"), contact.lastname());
    }

     public void addContactInGroup(ContactData contact, GroupData group) {
        openHomePage();
        selectContactById(contact.id());
        selectGroupFromDropdown(Integer.parseInt(group.id()));
        addToGroup();
    }

    private void selectGroupFromDropdown(int groupId) {
        click(By.name("to_group"));
        click(By.cssSelector("select[name='to_group'] option[value='" + groupId + "']"));
    }

    private void addToGroup() {
        click(By.name("add"));
    }

    public void deleteContactFromGroup(ContactData contact, GroupData group) {
        openHomePage();
        selectDropdown(group.id());
        selectContactById(contact.id());
        removeFromGroup(group.name());
    }

    private void removeFromGroup(String groupName) {
        click(By.xpath("//input[@name='remove' and contains(@value, 'Remove from')]"));
    }

    private void selectDropdown(String groupId) {
        new Select(manager.driver.findElement(By.name("group"))).selectByValue(groupId);
    }

    public String getPhones(ContactData contact) {
        return manager.driver.findElement(By.xpath(String.format("//input[@id='%s']/../../td[6]", contact.id()))).getText();
    }

    public Map<String, String> getPhones() {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var phones = row.findElements(By.tagName("td")).get(5).getText();
            result.put(id, phones);
        }
        return result;
    }

    public Map<String, String> getAddress() {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var address = row.findElements(By.tagName("td")).get(3).getText();
            result.put(id, address);
        }
        return result;
    }

    public Map<String, String> getEmail() {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var email = row.findElements(By.tagName("td")).get(4).getText();
            result.put(id, email);
        }
        return result;
    }

    public ContactData getContactModificationForm() {
        String address =  manager.driver.findElement(By.name("address")).getAttribute("value");
        String email = manager.driver.findElement(By.name("email")).getAttribute("value");
        String email2 = manager.driver.findElement(By.name("email2")).getAttribute("value");
        return new ContactData().withAddress(address).withEmail(email).withEmail2(email2);
    }
}