package model;

import java.io.Serializable;

public class Contact implements Serializable {
    private String name;
    private String phoneNumber;

    public Contact(String name, String phoneNumber) {
        /*if (!phoneNumber.matches("^\\+?\\d{10,13}$")) {
            throw new IllegalArgumentException("Invalid phone number format.");
        }*/
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone: " + phoneNumber;
    }
}
