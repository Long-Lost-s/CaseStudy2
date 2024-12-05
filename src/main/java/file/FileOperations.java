package file;

import model.PhoneBook;

import java.io.IOException;

public interface FileOperations {
    void savePhoneBook(PhoneBook phoneBook, String filename) throws IOException;
    PhoneBook loadPhoneBook(String filename) throws IOException, ClassNotFoundException;
}
