package ru.stqa.mantis.manager;

import java.io.IOException;


public class JamesCliHelper extends HelperBase {

    public JamesCliHelper(ApplicationManager manager) {
        super(manager);
    }

    public void addUser(String email, String password) throws InterruptedException, IOException {
//        CommandLine cmd = new CommandLine(
//                "java", "-cp", "\"james-server-jpa-app.lib/*\"",
//                "org.apache.james.cli.ServerCmd",
//                "AddUser", email, password);
//        cmd.execute();
//        cmd.waitFor();
//        try {
        Process command = new ProcessBuilder(
                "java", "-cp", "james-server-jpa-app.lib/*",
                "org.apache.james.cli.ServerCmd", "AddUser", email, password)
                .start();
        command.waitFor();

//        } catch (IOException | InterruptedException e) {
//            throw new RuntimeException("Failed to add user: " + email, e);
    }
}