package ru.stqa.mantis.model;


public record UserData(String username, String password, String realName, String email) {

    public UserData() {
        this("", "", "", "");
    }

    public UserData withUsername(String username) {
        return new UserData(username, this.password, this.realName, this.email);
    }

    public UserData withPassword(String password) {
        return new UserData(this.username, password, this.realName, this.email);
    }

    public UserData withRealName(String realName) {
        return new UserData(this.username, this.password, realName, this.email);
    }

    public UserData withEmail(String email) {
        return new UserData(this.username, this.password, this.realName, email);
    }
}