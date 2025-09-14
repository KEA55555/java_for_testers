package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class UserRegistrationTests extends TestBase {


    @Test
    void canRegisterUser() throws InterruptedException {
        app.registration().registerUser();
        app.http().syncCookiesFromBrowser();
        String username = app.registration().getRegisteredUser();
        String password = "password";
        app.http().login(username, password);
        Assertions.assertTrue(app.http().isLogged());
    }
}