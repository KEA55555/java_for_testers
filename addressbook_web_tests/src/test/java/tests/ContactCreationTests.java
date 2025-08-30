package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();
//        for (var firstname : List.of("", "contact firstname")) {
//            for (var lastname : List.of("", "contact lastname")) {
//                for (var address : List.of("", "contact address")) {
//                    for (var home : List.of("", "contact home")) {
//                        for (var email : List.of("", "contact email")) {
//                            result.add(new ContactData()
//                                    .withFirstName(firstname)
//                                    .withLastName(lastname)
//                                    .withAddress(address)
//                                    .withHome(home)
//                                    .withEmail(email));
//                        }
//                    }
//                }
//            }
//       }
        var mapper = new XmlMapper();

        var value = mapper.readValue(new File("contacts.xml"), new TypeReference<List<ContactData>>() {
        });
        result.addAll(value);
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        var oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var expectedList = new ArrayList<>(oldContacts);

        var lastContact = newContacts.get(newContacts.size() - 1);
        ContactData expectedContact = new ContactData()
                .withId(lastContact.id())
                .withFirstName(lastContact.firstname())
                .withLastName(lastContact.lastname());

        expectedList.add(expectedContact);
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }
}