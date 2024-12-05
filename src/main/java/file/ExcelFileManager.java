package file;

import model.Contact;
import model.PhoneBook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelFileManager implements FileOperations{
    @Override
    public void savePhoneBook(PhoneBook phoneBook, String filename) throws IOException {
        File file = new File(filename);

        // Tạo file nếu không tồn tại
        if (!file.exists()) {
            file.createNewFile();
        }

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Contacts");
            int rowIndex = 0;
            for (Contact contact : phoneBook.getContacts()) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(contact.getName());
                row.createCell(1).setCellValue(contact.getPhoneNumber());
            }

            try (FileOutputStream fileOut = new FileOutputStream(file)) {
                workbook.write(fileOut);
            }
        }
    }

    @Override
    public PhoneBook loadPhoneBook(String filename) throws IOException {
        File file = new File(filename);

        // Nếu file không tồn tại, tạo mới file Excel rỗng
        if (!file.exists()) {
            file.createNewFile();
            return new PhoneBook(); // Trả về danh bạ rỗng
        }

        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fis)) {
            PhoneBook phoneBook = new PhoneBook();
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                String name = row.getCell(0).getStringCellValue();
                String phoneNumber = row.getCell(1).getStringCellValue();
                phoneBook.addContact(new Contact(name, phoneNumber));
            }
            return phoneBook;
        }
    }
}
