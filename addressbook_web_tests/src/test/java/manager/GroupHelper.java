package manager;

import model.GroupData;
import org.openqa.selenium.By;

public class GroupHelper extends HelperBase {


    public GroupHelper(ApplicationManager manager) {
        super(manager);
    }

    public void openGroupsPage() {
        if (!isElementPresent(By.name("new"))) {
            click(By.linkText("groups"));
        }
    }

    public void createGroup(GroupData group) {
        openGroupsPage();
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupsPage();
    }

    public void removeGroup() {
        openGroupsPage();
        selectedGroup();
        removeSelectedGroups();
        click(By.cssSelector(".msgbox"));
        returnToGroupsPage();
    }

    public void modifyGroup(GroupData modifiedGroup) {
        openGroupsPage();
        selectedGroup();
        initCroupModification();
        fillGroupForm(modifiedGroup);
        submitGroupModification();
        returnToGroupsPage();
    }

    private void submitGroupCreation() {
        click(By.name("submit"));
    }

    private void initGroupCreation() {
        click(By.name("new"));
    }

    private void removeSelectedGroups() {
        click(By.name("delete"));
    }

    private void returnToGroupsPage() {
        click(By.linkText("group page"));
    }

    private void submitGroupModification() {
        click(By.name("update"));
    }

    private void fillGroupForm(GroupData group) {
        click(By.name("group_name"));
        type(By.name("group_name"), group.name());
        click(By.name("group_header"));
        type(By.name("group_header"), group.header());
        click(By.name("group_footer"));
        click(By.name("group_footer"));
        type(By.name("group_footer"), group.footer());
    }

    private void initCroupModification() {
        click(By.name("edit"));
    }

    private void selectedGroup() {
        click(By.name("selected[]"));
    }

    public int getCount() {
        openGroupsPage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void removeAllGroups() {
        openGroupsPage();
        selectAllGroups();
        removeSelectedGroups();
    }

    private void selectAllGroups() { //Цикл: перебор элементов коллекции
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }
}