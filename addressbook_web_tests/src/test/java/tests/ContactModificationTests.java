package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;

public class ContactModificationTests extends TestBase {

    @Test
    void canModifyContact() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData("id", "firstname",
                    "lastname", "address", "home", "email"));
        }
        var oldContacts = app.contacts().getList();
        var editContact = oldContacts.get(0);
        var testData = editContact.withFirstName("FirstName");
        app.contacts().modifyContact(testData);
        var newContacts = app.contacts().getList();
        var expectedList = new ArrayList<>(oldContacts);
        var index = expectedList.indexOf(editContact);
        expectedList.set(index, testData);
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }
}