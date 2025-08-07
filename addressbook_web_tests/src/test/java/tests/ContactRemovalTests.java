package tests;

import org.junit.jupiter.api.Test;

public class ContactRemovalTests extends TestBase {

    @Test
    public void canContactRemove() {
        app.openHomePage();
        app.checkButtonEdit();
        app.removeContact();
    }
}