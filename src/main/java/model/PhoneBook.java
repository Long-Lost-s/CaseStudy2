package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PhoneBook implements Serializable {
    private List<Contact> contacts = new ArrayList<>();

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void removeContact(Contact contact) {
        contacts.remove(contact);
    }
}
