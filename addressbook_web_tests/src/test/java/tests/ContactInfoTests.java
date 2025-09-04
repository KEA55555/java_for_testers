package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

    @Test
    void testPhones() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "Иван",
                    "Иванович", "Петров", "", "Москва", "email22", "email567", "", "5533", "1122", "7878", ""));
        }
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
                Stream.of(contact.home(), contact.mobile(), contact.work(), contact.secondary())
                        .filter(s -> s != null && !"".equals(s))
                        .collect(Collectors.joining("\n"))
        ));
        var phones = app.contacts().getPhones();
        Assertions.assertEquals(expected, phones);
    }

    @Test
    void testAddress() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "Иван",
                    "Иванович", "Петров", "", "Москва", "email22", "email567", "", "5533", "1122", "7878", ""));
        }
        var contacts = app.contacts().getList();
        var contactHomePage = contacts.stream().limit(1).collect(Collectors.toMap(ContactData::id, contact ->
                Stream.of(contact.address())
                        .filter(s -> s != null && !s.isEmpty())
                        .collect(Collectors.joining("\n"))
        ));
        app.contacts().initContactModification(0);

        var getContactModificationForm = app.contacts().getContactModificationForm();
        var contactModificationForm = contacts.stream()
                .filter(contact -> contactHomePage.containsKey(contact.id()))
                .collect(Collectors.toMap(ContactData::id, contact -> {
                            ContactData addressForm = app.contacts().getContactModificationForm();
                            return Stream.of(addressForm.address())
                                    .filter(s -> s != null && !s.isEmpty())
                                    .collect(Collectors.joining("\n"));
                        }
                ));
        Assertions.assertEquals(contactHomePage, contactModificationForm);
    }

    @Test
    void testEmail() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "Иван",
                    "Иванович", "Петров", "", "Москва", "email22", "email567", "", "5533", "1122", "7878", ""));
        }
        var contacts = app.contacts().getList();
        var contactHomePage = contacts.stream().limit(1).collect(Collectors.toMap(ContactData::id, contact ->
                Stream.of(contact.email())
                        .filter(s -> s != null && !s.isEmpty())
                        .collect(Collectors.joining("\n"))
        ));
        app.contacts().initContactModification(0);

        var getContactModificationForm = app.contacts().getContactModificationForm();
        var contactModificationForm = contacts.stream()
                .filter(contact -> contactHomePage.containsKey(contact.id()))
                .collect(Collectors.toMap(ContactData::id, contact -> {
                            ContactData emailForm = app.contacts().getContactModificationForm();
                            return Stream.of(emailForm.email(), emailForm.email2())
                                    .filter(s -> s != null && !s.isEmpty())
                                    .collect(Collectors.joining("\n"));
                        }
                ));
        app.contacts().returnToHomePage();

        Assertions.assertEquals(contactHomePage, contactModificationForm);
    }
}