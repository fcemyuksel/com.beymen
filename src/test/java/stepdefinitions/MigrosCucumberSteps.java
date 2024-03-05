package stepdefinitions;

import Pages.MigrosPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class MigrosCucumberSteps {

    WebDriver driver;
    MigrosPage migrosPage = new MigrosPage();
    Random random = new Random();
    JavascriptExecutor jse = (JavascriptExecutor) driver;
    private double harcamakIstedigiTutar;
    private double sepettekiToplamTutar;
    Set<String> secilenUrunler = new HashSet<>();
    //Actions actions = new Actions(driver);

    @Given("Kullanicidan alisveris limiti belirlemesi istenir.")
    public void kullanicidan_alisveris_limiti_belirlemesi_istenir() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Harcamak istediğiniz tutari giriniz: ");
        harcamakIstedigiTutar = 0;


    }

    @When("Kullanici https:\\/\\/www.migros.com.tr\\/ sitesine gider")
    public void kullanici_https_www_migros_com_tr_sitesine_gider() {
        Driver.getDriver().get(ConfigReader.getProperty("migrosUrl"));
    }

    @When("Kullanici pop-up ve cerezleri kapatir.")
    public void kullanici_pop_up_ve_cerezleri_kapatir() {
        ReusableMethods.bekle(2);
        migrosPage.cerezReddetElementi.click();
    }

    @When("Kullanici sitenin dogru oldugunu kontrol eder.")
    public void kullanici_sitenin_dogru_oldugunu_kontrol_eder() {
        Assert.assertTrue(migrosPage.uyeOlElementi.isDisplayed());
        ReusableMethods.bekle(2);
    }

    @Then("Adres belirleme islemi gerceklestirilir")
    public void Adres_belirleme_islemi_gerceklestirilir() {
        migrosPage.adresTanimlamaElementi.click();
        migrosPage.adresimeGelsinElementi.click();
        ReusableMethods.bekle(1);
        migrosPage.mevcutKonumuKullan.click();
        migrosPage.konumEkleButonu.click();
        ReusableMethods.bekle(1);
        migrosPage.adresimDogruButonu.click();
    }

    @When("Kullanici Migros Hemen sekmesini secer.")
    public void kullanici_migros_hemen_sekmesini_secer() {
        migrosPage.migrosHemenElementi.click();

    }

    @Then("I verify hidden element text")
    public void verifyHiddenElementText() {
        String text = migrosPage.getHiddenElementText();

        ReusableMethods.bekle(2);
    }

    @When("Kullanici kategoriler kismindan Temel Gida'yi secer.")
    public void kullanici_kategoriler_kismindan_temel_gida_yi_secer() {
        migrosPage.getHiddenElementText();
        ReusableMethods.bekle(2);
        migrosPage.hiddenElement.click();
    }

    @When("Temel gidadan alt kategori random olarak secilir")
    public void temel_gidadan_alt_kategori_random_olarak_secilir() {
        int randomIndex = random.nextInt(migrosPage.urunlerElementListesi.size());
        ReusableMethods.bekle(2);
        WebElement selectedProduct = migrosPage.urunlerElementListesi.get(randomIndex);
        selectedProduct.click();

    }
/*
    @When("kullanicidan alinan tutar kadar random urun sepete atilir.")
    public void kullanicidan_alinan_tutar_kadar_random_urun_sepete_atilir() {
        migrosPage.sepeteEkleElementi.click();

        migrosPage.migrosHemenElementi.click();
        int randomIndex = random.nextInt(migrosPage.temelGidaElementListesi.size());
        WebElement selectedProduct1 = migrosPage.temelGidaElementListesi.get(randomIndex);
        selectedProduct1.click();

    }
*/
    @When("Kullanici sepete gider tutarin belirlenen tutardan yuksek olmadigini teyid eder")
    public void kullanici_sepete_gider_tutarin_belirlenen_tutardan_yuksek_olmadigini_teyid_eder() {
        double wallet = 0;
        double cart = 17050;
        while (wallet < cart) {
            List<WebElement> chosenCategoryProductList = driver.findElements(By.xpath("//div[@class='product-cards']/*"));
            //List<ProductDetail> productDetailList = new ArrayList<>();
            int randomIndex = random.nextInt(chosenCategoryProductList.size());
            //ProductDetail productDetail = new ProductDetail();

            WebElement productAllBody = chosenCategoryProductList.get(randomIndex).findElement(By.xpath("//div[@class='product-cards']"));
            WebElement addItemsToCartElement = chosenCategoryProductList.get(randomIndex).findElement(By.xpath("//*[@class='ng-fa-icon add-to-cart-button ng-star-inserted']"));
            jse.executeScript("arguments[0].click();", addItemsToCartElement);


            String productPrice = productAllBody.findElement(By.className("amount")).getText();
            //productDetail.setProductPrice(productPrice.replaceAll("\\D", ""));

            String tempProductPrice = productAllBody.findElement(By.className("amount")).getText();
            double tempProductPriceDbl = Double.parseDouble(tempProductPrice.replaceAll("\\D", ""));

            //cart += tempProductPriceDbl;
            wallet += tempProductPriceDbl;

            System.out.println(randomIndex);
        }
    }
}
