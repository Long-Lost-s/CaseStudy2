package file;

import model.PhoneBook;

import java.io.*;

public class BinaryFileManager implements FileOperations{
    @Override
    public void savePhoneBook(PhoneBook phoneBook, String filename) throws IOException {
        File file = new File(filename);

        // Tạo file nếu không tồn tại
        if (!file.exists()) {
            file.createNewFile();
        }

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(phoneBook);
        }
    }

    @Override
    public PhoneBook loadPhoneBook(String filename) throws IOException {
        File file = new File(filename);

        // Nếu file không tồn tại, tạo mới file nhị phân rỗng
        if (!file.exists()) {
            file.createNewFile();
            return new PhoneBook(); // Trả về một danh bạ rỗng
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (PhoneBook) in.readObject();
        } catch (ClassNotFoundException e) {
            // Bắt lỗi ClassNotFoundException và ném ra IOException
            throw new IOException("Class not found: " + e.getMessage(), e);
        }
    }
}
