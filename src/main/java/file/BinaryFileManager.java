package file;

import model.PhoneBook;

import java.io.*;

public class BinaryFileManager implements FileOperations{
    @Override
    public void savePhoneBook(PhoneBook phoneBook, String filename) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(phoneBook);
        }
    }

    @Override
    public PhoneBook loadPhoneBook(String filename) throws IOException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (PhoneBook) in.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException("Class not found", e);
        }
    }
}
