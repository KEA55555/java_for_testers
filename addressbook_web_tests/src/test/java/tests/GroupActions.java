package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;

public class GroupActions extends TestBase {

    @Test
    void addContactInGroup() {
        app.contacts().selectContactAndGroup();
        var addContact = app.contacts().getSelectedContact();
        var addGroup = app.contacts().getSelectedGroup();

        var contactsInGroup = app.hbm().getContactsInGroup(addGroup);
        app.contacts().addContactInGroup(addContact, addGroup);
        var newContactsInGroup = app.hbm().getContactsInGroup(addGroup);

        var expectedList = new ArrayList<>(contactsInGroup);
        expectedList.add(addContact);

        Comparator<ContactData> compareById = (o1, o2) ->
                Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));

        newContactsInGroup.sort(compareById);
        expectedList.sort(compareById);

        Assertions.assertEquals(expectedList, newContactsInGroup);
    }

    @Test
    void deleteContactFromGroup() {
        app.contacts().selectContactAndGroupForDelete();
        var deleteContact = app.contacts().getSelectedContact();
        var groupWithContact = app.contacts().getSelectedGroup();

        var contactsInGroup = app.hbm().getContactsInGroup(groupWithContact);
        app.contacts().deleteContactFromGroup(deleteContact, groupWithContact);
        var newContactsInGroup = app.hbm().getContactsInGroup(groupWithContact);

        var expectedList = new ArrayList<>(contactsInGroup);
        expectedList.remove(deleteContact);

        Comparator<ContactData> compareById = (o1, o2) ->
                Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));

        newContactsInGroup.sort(compareById);
        expectedList.sort(compareById);

        Assertions.assertEquals(expectedList, newContactsInGroup);
    }
}