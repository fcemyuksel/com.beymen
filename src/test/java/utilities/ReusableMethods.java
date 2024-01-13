package utilities;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReusableMethods {

    public static void bekle(int saniye)  {
        try {
            Thread.sleep(saniye*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    public static void dosyaYolu(String dosyaYolu) {


        dosyaYolu = "src/test/java/Pages/Beymen.xlsx";
        FileInputStream fileInputStream;


        {
            try {
                fileInputStream = new FileInputStream(dosyaYolu);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    Workbook workbook;

    {
        try {
            workbook = WorkbookFactory.create(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }





}
}
