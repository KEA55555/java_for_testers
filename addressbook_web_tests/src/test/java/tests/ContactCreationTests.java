package tests;

import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void canContactCreate() {
        app.createContact();
    }

    @Test
    public void canContactCreateWithEmptyName() {
        app.createContact();
    }
}