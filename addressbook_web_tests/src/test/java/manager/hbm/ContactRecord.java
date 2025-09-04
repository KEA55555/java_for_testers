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

//        public Date deprecated = new Date();

    public ContactRecord() {
    }

    public ContactRecord(int id, String firstname, String middlename, String lastname, String nickname, String address) {
        this.id = id;
        this.firstname = firstname;
        this.middlename = middlename;
        this.nickname = nickname;
        this.lastname = lastname;
        this.address = address;

    }
}
