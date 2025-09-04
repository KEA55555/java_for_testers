package manager.hbm;

import jakarta.persistence.*;

@Entity
@Table(name = "addressbook")
public class ContactRecord {

    @Id
    public int id;

    public String firstname;

    public String middlename;

    public String lastname;

    public String nickname;

    public String address;

    public String home;

    public String mobile;

    public String work;

    public String phone2;

    public String email;

    public String email2;

    public String company = "";
    public String title = "";
    public String fax = "";
    public String email3 = "";
    public String homepage = "";


//        public Date deprecated = new Date();

    public ContactRecord() {
    }

    public ContactRecord(int id, String firstname, String middlename, String lastname, String nickname, String address, String email, String email2) {
        this.id = id;
        this.firstname = firstname;
        this.middlename = middlename;
        this.nickname = nickname;
        this.lastname = lastname;
        this.address = address;
        this.email = email;
        this.email2 = email2;
    }
}
