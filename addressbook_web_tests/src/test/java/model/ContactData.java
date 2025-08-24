package model;


public record ContactData(String firstname, String lastname, String address, String home, String email) {

    public ContactData() {
        this("", "", "", "", "");
    }

    public ContactData withFirstName(String firstname) {
        return new ContactData(firstname, this.lastname, this.address, this.home, this.email);
    }

    public ContactData withLastName(String lastname) {
        return new ContactData(this.firstname, lastname, this.address, this.home, this.email);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.firstname, this.lastname, address, this.home, this.email);
    }

    public ContactData withHome(String home) {
        return new ContactData(this.firstname, this.lastname, this.address, home, this.email);
    }

    public ContactData withEmail(String email) {
        return new ContactData(this.firstname, this.lastname, this.address, this.home, email);
    }
}