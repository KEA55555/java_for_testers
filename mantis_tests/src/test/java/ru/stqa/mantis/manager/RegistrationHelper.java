package ru.stqa.mantis.manager;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import ru.stqa.mantis.common.CommonFunctions;

import java.time.Duration;
import java.util.regex.Pattern;

public class RegistrationHelper extends HelperBase {

    private String registeredUser;

    public RegistrationHelper(ApplicationManager manager) {
        super(manager);
    }

    public void registerUser() throws InterruptedException {
        String username = CommonFunctions.randomString(5);
        String email = String.format("%s@localhost", CommonFunctions.randomString(8));
        this.registeredUser = username;

        manager.jamesCli().addUser(email, "password");
        manager.mail().drain(email, "password");
        signupForNewAccount(username, email);

        var messages = manager.mail().receive(email, "password", Duration.ofSeconds(60));
        var text = messages.get(0).content();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        String url = null;
        if (matcher.find()) {
            url = text.substring(matcher.start(), matcher.end());
            System.out.println(url);
        }
        Assertions.assertNotNull(url, "URL not found");
        manager.driver().get(url);

        completedRegistration(username, "password", "password");
    }

    public void signupForNewAccount(String username, String email) {
        click(By.cssSelector("a.back-to-login-link.pull-left[href='signup_page.php']"));
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.cssSelector("input[type='submit']"));
    }

    public void completedRegistration(String realname, String password, String password_confirm) {
        type(By.name("realname"), realname);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password_confirm);
        click(By.xpath("//span[@class='bigger-110' and text()='Update User']"));
    }

    public String getRegisteredUser() {
        return registeredUser;
    }
}