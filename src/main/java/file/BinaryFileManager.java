package file;

import model.PhoneBook;

import java.io.*;

public class BinaryFileManager implements FileOperations{
    @Override
    public void savePhoneBook(PhoneBook phoneBook, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(phoneBook);
        }
    }

    @Override
    public PhoneBook loadPhoneBook(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (PhoneBook) ois.readObject();
        }
    }
}
