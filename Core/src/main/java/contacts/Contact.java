package contacts;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Контакт
 */
@Entity
@Table(name = "contact")
@NamedQuery(name = "Contact.getAll", query = "SELECT c from Contact c")
public class Contact {

    @Column(name = "id")
    @Id
    @GeneratedValue
    Long id;


    String surname;
    String name;

    public Contact() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return "Contact{" + "id = " + id + ", name = " + name + ", surname = " + surname + "}";
    }
}