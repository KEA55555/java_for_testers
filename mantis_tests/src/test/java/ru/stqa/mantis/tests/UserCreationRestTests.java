package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class UserCreationRestTests extends TestBase {

    @Test
    void canCreateUserRest() throws InterruptedException {
        app.rest().createUser();
        app.http().syncCookiesFromBrowser();
        String username = app.rest().getCreatedUsername();
        String password = "password";
        app.http().login(username, password);
        Assertions.assertTrue(app.http().isLogged());
        app.http().syncCookiesFromBrowser();
    }
}