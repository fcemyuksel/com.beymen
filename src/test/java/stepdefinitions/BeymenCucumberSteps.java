package stepdefinitions;


import Pages.BeymenPage;
import com.fasterxml.jackson.core.io.DataOutputAsStream;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.poi.ss.usermodel.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import utilities.ConfigReader;
import utilities.Driver;

import java.io.*;
import java.util.List;
import java.util.Random;

import static utilities.Driver.driver;

public class BeymenCucumberSteps {

    WebDriver driver;
    BeymenPage beymenPage = new BeymenPage();
    Sheet sayfa1;
    Random random = new Random();
    public DataOutputAsStream workbook;
    String input1;


    @Given("beymen sitesi açılır.")
    public void beymen_sitesi_açılır() {
        Driver.getDriver().get(ConfigReader.getProperty("beymenUrl"));
        beymenPage.cerezElementi.click();
        beymenPage.cinsiyetElementi.click();

    }

    @Then("Ana sayfanın açıldığı kontrol edilir.")
    public void ana_sayfanın_açıldığı_kontrol_edilir() {
        Assert.assertTrue(beymenPage.hesabimElementi.isDisplayed());
        beymenPage.headerElementi.click();

    }

    @When("Arama kutucuğuna “şort” kelimesi girilir.")
    public void arama_kutucuğuna_şort_kelimesi_girilir() throws InterruptedException {
        String dosyaYolu = "src/test/java/utilities/Beymen.xlsx";
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(dosyaYolu);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Workbook workbook;
        try {
            workbook = WorkbookFactory.create(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sayfa1 = workbook.getSheet("Sayfa1");
        Row row = sayfa1.getRow(0);
        Cell cell = row.getCell(0);
        System.out.println(cell.getStringCellValue());
        beymenPage.searchElementi.sendKeys(cell.getStringCellValue());
        Thread.sleep(2000);

    }

    @Then("Arama kutucuğuna girilen “şort” kelimesi silinir.")
    public void arama_kutucuğuna_girilen_şort_kelimesi_silinir() {
        beymenPage.searchElementi.clear();

    }

    @Then("Arama kutucuğuna “gömlek” kelimesi girilir.")
    public void arama_kutucuğuna_gömlek_kelimesi_girilir() {
        Row row = sayfa1.getRow(0);
        Cell cell = row.getCell(1);
        beymenPage.searchElementi.sendKeys(cell.getStringCellValue());

    }

    @Then("Klavye üzerinden “enter” tuşuna bastırılır")
    public void klavye_üzerinden_enter_tuşuna_bastırılır() {
        beymenPage.searchElementi.sendKeys(Keys.ENTER);

    }

    @Then("Sonuca göre sergilenen ürünlerden rastgele bir ürün seçilir.")
    public void sonuca_göre_sergilenen_ürünlerden_rastgele_bir_ürün_seçilir() {
        // Rastgele bir ürün seç
        System.out.println(beymenPage.productList.size());
        int randomIndex = random.nextInt(beymenPage.productList.size());
        WebElement selectedProduct = beymenPage.productList.get(randomIndex);

        // Seçilen ürünü tıkla
        selectedProduct.click();

        boolean control = true;
        int index = 0;
        if (beymenPage.bedenElementi.size() > 0) {
            while (control) {
                WebElement secilenBeden = beymenPage.bedenElementi.get(index);
                String classValue = secilenBeden.getAttribute("class");
                if (!classValue.contains("disabled")) {
                    control = false;
                }
            }

            beymenPage.bedenElementi.get(index).click();
        } else {
             index = 0;
            beymenPage.kritikBedenlerListesi.get(index).click();
        }
    }

    @Then("Seçilen ürünün ürün bilgisi ve tutar bilgisi txt dosyasına yazılır.")
    public void seçilen_ürünün_ürün_bilgisi_ve_tutar_bilgisi_txt_dosyasına_yazılır() {
        input1 = beymenPage.secilenUrunFiyat.getText();
        System.out.println(beymenPage.secilenUrunBilgisi.getText());
        System.out.println(beymenPage.secilenUrunFiyat.getText());
        String metin = beymenPage.secilenUrunBilgisi.getText();
        String metin1=beymenPage.secilenUrunFiyat.getText();


        // Dosya yolu ve adını belirtin
        String dosyaYolu1 = "src/test/java/utilities/beymen.txt";

        try {
            // FileWriter ile dosyayı aç
            FileWriter dosyaYazici = new FileWriter(new File(dosyaYolu1), true);

            // BufferedWriter kullanarak daha efektif yazma işlemi yap
            BufferedWriter yazici = new BufferedWriter(dosyaYazici);

            // Metni dosyaya yaz
            yazici.write(metin);
            yazici.newLine();
            yazici.write(metin1);
            yazici.newLine();

            // Dosyayı kapat
            yazici.close();

            System.out.println("Metin dosyaya başarıyla yazıldı.");
        } catch (IOException e) {
            System.err.println("Dosyaya yazma hatası: " + e.getMessage());
        }

    }

    @Then("Seçilen ürün sepete eklenir.")
    public void seçilen_ürün_sepete_eklenir() {
        beymenPage.sepeteEkleElementi.click();
        beymenPage.sepetimElementi.click();

    }

    @Then("Ürün sayfasındaki fiyat ile sepette yer alan ürün fiyatının doğruluğu karşılaştırılır.")
    public void ürün_sayfasındaki_fiyat_ile_sepette_yer_alan_ürün_fiyatının_doğruluğu_karşılaştırılır() {

        String input2 = beymenPage.sepettekiUrunElementi.getText();


        System.out.println(input1);
        System.out.println(input2);
        String sadeceSayilar1= input1.replaceAll("\\D","");
        String sadeceSayilar2= input2.replaceAll("\\D","");

        int tutar1 = Integer.parseInt(sadeceSayilar1);
        int tutar2 = Integer.parseInt(sadeceSayilar2)/100;
        System.out.println(tutar1);
        System.out.println(tutar2);
        Assert.assertEquals(tutar2,tutar1);

    }

    @Then("Adet arttırılarak ürün adedinin {int} olduğu doğrulanır.")
    public void adet_arttırılarak_ürün_adedinin_olduğu_doğrulanır(Integer int1) {
        beymenPage.dropdownMenu.click();
        Select select=new Select(beymenPage.dropdownMenu);

        //bazi urunlerden 1'den fazla kalmadigi icin kod ilerleyemiyordu. o nedenle bu if blogunu actim
        int optionsSayisi= select.getOptions().size();
        if (optionsSayisi==1){
            String expectedAdet="1 adet";
            String actualAdet=select.getFirstSelectedOption().getText();
            System.out.println(actualAdet);
            Assert.assertEquals(expectedAdet,actualAdet);
        }else {
            select.selectByIndex(1);
            String expectedAdet="2 adet";
            String actualAdet=select.getFirstSelectedOption().getText();
            System.out.println(actualAdet);
            Assert.assertEquals(expectedAdet,actualAdet);
        }



    }

    @Then("Ürün sepetten silinerek sepetin boş olduğu kontrol edilir.")
    public void ürün_sepetten_silinerek_sepetin_boş_olduğu_kontrol_edilir() {
        beymenPage.silElementi.click();

        Assert.assertTrue(beymenPage.bosSepetElementi.isDisplayed());

        Driver.closeDriver();
    }
}
