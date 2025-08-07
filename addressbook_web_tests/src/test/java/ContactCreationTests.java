import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void canContactCreate() {
        createContact();
    }

    @Test
    public void canContactCreateWithEmptyName() {
        createContact();
    }
}