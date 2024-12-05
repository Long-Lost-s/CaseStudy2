package org.example;

import model.Contact;
import model.PhoneBook;
import file.BinaryFileManager;
import file.ExcelFileManager;
import file.FileOperations;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Tạo danh bạ và thêm vài liên hệ
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.addContact(new Contact("Alice", "123456789"));
        phoneBook.addContact(new Contact("Bob", "987654321"));

        // Hiển thị danh bạ trước khi lưu
        System.out.println("Danh bạ trước khi lưu:");
        for (Contact contact : phoneBook.getContacts()) {
            System.out.println(contact.getName() + ": " + contact.getPhoneNumber());
        }

        // Lưu danh bạ vào file nhị phân
        FileOperations binaryManager = new BinaryFileManager();
        String binaryFileName = "phonebook.dat";
        try {
            binaryManager.savePhoneBook(phoneBook, binaryFileName);
            System.out.println("\nDanh bạ đã được lưu vào file nhị phân: " + binaryFileName);

            // Tải lại danh bạ từ file nhị phân
            PhoneBook loadedPhoneBookFromBinary = binaryManager.loadPhoneBook(binaryFileName);
            System.out.println("\nDanh bạ đã được tải từ file nhị phân:");
            for (Contact contact : loadedPhoneBookFromBinary.getContacts()) {
                System.out.println(contact.getName() + ": " + contact.getPhoneNumber());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Lưu danh bạ vào file Excel
        FileOperations excelManager = new ExcelFileManager();
        String excelFileName = "phonebook.xlsx";
        try {
            excelManager.savePhoneBook(phoneBook, excelFileName);
            System.out.println("\nDanh bạ đã được lưu vào file Excel: " + excelFileName);

            // Tải lại danh bạ từ file Excel
            PhoneBook loadedPhoneBookFromExcel = excelManager.loadPhoneBook(excelFileName);
            System.out.println("\nDanh bạ đã được tải từ file Excel:");
            for (Contact contact : loadedPhoneBookFromExcel.getContacts()) {
                System.out.println(contact.getName() + ": " + contact.getPhoneNumber());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}