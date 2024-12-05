package file;

import model.Contact;
import model.PhoneBook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelFileManager implements FileOperations{
    @Override
    public void savePhoneBook(PhoneBook phoneBook, String filename) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Contacts");

        int rowIndex = 0;
        for (Contact contact : phoneBook.getContacts()) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(contact.getName());
            row.createCell(1).setCellValue(contact.getPhoneNumber());
        }

        try (FileOutputStream fileOut = new FileOutputStream(filename)) {
            workbook.write(fileOut);
        }
        workbook.close();
    }

    @Override
    public PhoneBook loadPhoneBook(String filename) throws IOException {
        PhoneBook phoneBook = new PhoneBook();

        try (FileInputStream fis = new FileInputStream(filename);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                String name = row.getCell(0).getStringCellValue();
                String phoneNumber = row.getCell(1).getStringCellValue();
                phoneBook.addContact(new Contact(name, phoneNumber));
            }
        }
        return phoneBook;
    }
}
