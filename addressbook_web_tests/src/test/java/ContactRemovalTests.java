import org.junit.jupiter.api.Test;

public class ContactRemovalTests extends TestBase {

    @Test
    public void canContactRemove() {
        openHomePage();
        checkButtonEdit();
        removeContact();
    }
}