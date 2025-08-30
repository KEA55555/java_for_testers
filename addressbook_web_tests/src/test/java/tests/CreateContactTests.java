package tests;

import model.ContactData;
import ru.stqa.addressbook.common.CommonFunctions;

public class CreateContactTests extends TestBase {


    void createContact() {
        var contact = new ContactData()
                .withFirstName(CommonFunctions.randomString(10)).withLastName(CommonFunctions.randomString(10));
        app.contacts().createContact(contact);
    }
}
