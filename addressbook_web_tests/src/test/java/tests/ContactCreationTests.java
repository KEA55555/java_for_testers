package tests;

import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void canContactCreate() {
        app.contact().createContact();
    }

    @Test
    public void canContactCreateWithEmptyName() {
        app.contact().createContact();
    }
}
