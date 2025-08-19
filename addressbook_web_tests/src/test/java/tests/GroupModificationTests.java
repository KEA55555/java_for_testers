package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupModificationTests extends TestBase {

    @Test
    void canModifyGroup() {
        if (app.groups().getCount() == 0) {
            app.groups().removeGroup();
        }
        app.groups().modifyGroup(new GroupData().withName("modified name"));
    }
}
