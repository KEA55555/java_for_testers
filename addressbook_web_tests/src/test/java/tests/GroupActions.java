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
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "FirstName", "LastName", "sity", "7777", "", "")); //предусловие
        }
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer")); //предусловие
        }
        var group = app.hbm().getGroupList().get(0);
        var oldRelated = app.hbm().getContactsInGroup(group);
        var contact = app.contacts().getList();
        app.contacts().addContactInGroup(contact, group);
        var expectedList = new ArrayList<>(oldRelated);

        var newRelated = app.hbm().getContactsInGroup(group);

        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newRelated.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newRelated, expectedList);
    }

    @Test
    void deleteContactFromGroup() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "FirstName", "LastName", "sity", "7777", "", "")); //предусловие
        }
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer")); //предусловие
        }
        var group = app.hbm().getGroupList().get(0);
        var oldRelated = app.hbm().getContactsInGroup(group);
        var contact = oldRelated.get(0);
        app.contacts().deleteContactFromGroup(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        var expectedList = new ArrayList<>(oldRelated);
//        expectedList.remove(contact);

        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newRelated.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newRelated, expectedList);
    }
}