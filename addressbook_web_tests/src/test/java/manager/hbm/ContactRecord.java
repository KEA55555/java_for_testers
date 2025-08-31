package manager.hbm;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
    @Table(name = "addressbook")
    public class ContactRecord {

        @Id
        public int id;

        public String firstname;

        public String lastname;

        public String address;

//        public Date deprecated = new Date();

        public ContactRecord() {
        }

        public ContactRecord(int id, String firstname, String lastname, String address) {
            this.id = id;
            this.firstname = firstname;
            this.lastname = lastname;
            this.address = address;
        }
    }
