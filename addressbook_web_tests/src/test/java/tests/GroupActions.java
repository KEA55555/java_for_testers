package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;

public class GroupActions extends TestBase {

    @Test
    void addContactInGroup() {
        var contacts = app.hbm().getContactList();
        var groups = app.hbm().getGroupList();
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "Иван",
                    "Иванович", "Петров", "", "Москва", "email22",
                    "email567", "", "5533", "1122", "7878", ""));
            contacts = app.hbm().getContactList();
        }
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
            groups = app.hbm().getGroupList();
        }
        ContactData addContact = null;
        GroupData addGroup = null;
        for (var group : groups) {
            var contactsInGroups = app.hbm().getContactsInGroup(group);
            for (var contact : contacts) {
                if (!contactsInGroups.contains(contact)) {
                    addContact = contact;
                    addGroup = group;
                    break;
                }
            }
            if (addContact != null) break;
        }
        if (addContact == null) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
            addContact = contacts.get(0);
            groups = app.hbm().getGroupList();
        }
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
        var contacts = app.hbm().getContactList();
        var groups = app.hbm().getGroupList();
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "Иван",
                    "Иванович", "Петров", "", "Москва", "email22",
                    "email567", "", "5533", "1122", "7878", ""));
            contacts = app.hbm().getContactList();
        }
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
            groups = app.hbm().getGroupList();
        }
        ContactData deleteContact = null;
        GroupData groupWithContact = null;
        for (var group : groups) {
            var contactsInGroups = app.hbm().getContactsInGroup(group);
            if (!contactsInGroups.isEmpty()) {
                deleteContact = contactsInGroups.get(0);
                groupWithContact = group;
                break;
            }
        }
        if (deleteContact == null) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
            groups = app.hbm().getGroupList();
            groupWithContact = groups.get(groups.size() - 1);

            deleteContact = contacts.get(0);
            app.contacts().addContactInGroup(deleteContact, groupWithContact);
        }

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