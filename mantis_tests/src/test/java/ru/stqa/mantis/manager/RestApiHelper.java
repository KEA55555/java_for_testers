package ru.stqa.mantis.manager;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.Configuration;
import io.swagger.client.api.IssuesApi;
import io.swagger.client.api.UserApi;
import io.swagger.client.auth.ApiKeyAuth;
import io.swagger.client.model.*;
import org.junit.jupiter.api.Assertions;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.IssueData;
import ru.stqa.mantis.model.UserData;

import java.time.Duration;
import java.util.regex.Pattern;

public class RestApiHelper extends HelperBase {


    public RestApiHelper(ApplicationManager manager) {
        super(manager);
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost/mantisbt-2.25.0/api/rest");
        ApiKeyAuth Authorization = (ApiKeyAuth) defaultClient.getAuthentication("Authorization");
        Authorization.setApiKey(manager.property("apiKey"));
    }

    public void createIssue(IssueData issueData) {
        Issue issue = new Issue();
        issue.setSummary(issueData.summary());

        issue.setDescription(issueData.description());
        var projectId = new Identifier();
        projectId.setId(issueData.project());
        issue.setProject(projectId);

        var categoryId = new Identifier();
        categoryId.setId(issueData.category());
        issue.setCategory(categoryId);

        IssuesApi apiInstance = new IssuesApi();
        try {
            apiInstance.issueAdd(issue);
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void createUser() throws InterruptedException {
        String username = CommonFunctions.randomString(5);
        String email = String.format("%s@localhost", CommonFunctions.randomString(8));
        String realName = username;
        String password = "password";

        //регистрируем почту
        manager.jamesApi().addUser(email, password);
        manager.mail().drain(email, password);

        //регистрируем пользователя в мантис через rest
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRealName(realName);
        user.setEmail(email);

        UserApi userApiInstance = new UserApi();
        try {
            userApiInstance.userAdd(user);
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }

        //извлекаем ссылку из письма
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

        UserData userData = new UserData(username, password, realName, email);

        // Вызов метода из RegistrationHelper
        manager.registration().completedRegistration(
                userData.realName(),
                userData.password(),
                userData.password()
        );
    }
}
