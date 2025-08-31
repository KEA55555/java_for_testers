package model;


public record ContactData(String id, String firstname, String lastname, String address, String home, String email, String photo) {

    public ContactData() {
        this("", "", "", "", "", "", "");
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.firstname, this.lastname, this.address, this.home, this.email, this.photo);
    }

    public ContactData withFirstName(String firstname) {
        return new ContactData(this.id, firstname, this.lastname, this.address, this.home, this.email, this.photo);
    }

    public ContactData withLastName(String lastname) {
        return new ContactData(this.id, this.firstname, lastname, this.address, this.home, this.email, this.photo);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.firstname, this.lastname, address, this.home, this.email, this.photo);
    }

    public ContactData withHome(String home) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, home, this.email, this.photo);
    }

    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, this.home, email, this.photo);
    }

    public ContactData withPhoto(String photo) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, this.home, this.email, photo);
    }
}