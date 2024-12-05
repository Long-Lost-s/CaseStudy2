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
        Workbook workbook = new XSSFWorkbook(); // Tạo một workbook mới cho Excel.
        Sheet sheet = workbook.createSheet("Contacts"); // Tạo một sheet mới với tên "Contacts".

        int rowIndex = 0;
        // Duyệt qua danh sách các contact trong PhoneBook và ghi thông tin vào Excel.
        for (Contact contact : phoneBook.getContacts()) {
            Row row = sheet.createRow(rowIndex++); // Tạo một dòng mới trong sheet.
            row.createCell(0).setCellValue(contact.getName()); // Ghi tên vào ô đầu tiên.
            row.createCell(1).setCellValue(contact.getPhoneNumber()); // Ghi số điện thoại vào ô thứ hai.
        }

        // Ghi workbook vào tệp Excel.
        try (FileOutputStream fileOut = new FileOutputStream(filename)) {
            workbook.write(fileOut); // Ghi dữ liệu vào tệp.
        }
        workbook.close(); // Đảm bảo đóng workbook sau khi ghi xong.
    }

    @Override
    public PhoneBook loadPhoneBook(String filename) throws IOException {
        PhoneBook phoneBook = new PhoneBook(); // Tạo một PhoneBook mới để lưu trữ danh bạ.
        try (FileInputStream fis = new FileInputStream(filename); // Mở tệp Excel.
             Workbook workbook = new XSSFWorkbook(fis)) { // Đọc tệp Excel với XSSFWorkbook.

            Sheet sheet = workbook.getSheetAt(0); // Lấy sheet đầu tiên (sheet chứa danh bạ).
            for (Row row : sheet) {
                // Đọc tên và số điện thoại từ các ô trong dòng.
                String name = row.getCell(0).getStringCellValue(); // Lấy tên từ ô đầu tiên.
                String phoneNumber = row.getCell(1).getStringCellValue(); // Lấy số điện thoại từ ô thứ hai.
                // Tạo một đối tượng Contact và thêm vào PhoneBook.
                phoneBook.addContact(new Contact(name, phoneNumber));
            }
        }
        return phoneBook; // Trả về danh bạ đã được nạp từ tệp Excel.
    }
}
