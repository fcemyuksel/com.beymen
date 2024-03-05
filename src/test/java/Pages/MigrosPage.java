package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class MigrosPage {

    public MigrosPage(){

        WebDriver driver;


        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//div[@class='choose-address-container']")
    public WebElement adresTanimlamaElementi;

    @FindBy(xpath = "//*[text()='Adresime Gelsin']")
    public WebElement adresimeGelsinElementi;

    @FindBy(xpath = "//*[text()=' Mevcut Konumu Kullan ']")
    public WebElement mevcutKonumuKullan;

    @FindBy(xpath = "//*[text()=' İşaretlediğim Konumu Ekle ']")
    public WebElement konumEkleButonu;

    @FindBy(xpath = "//*[text()=' Evet, Adresim Doğru ']")
    public WebElement adresimDogruButonu;

    @FindBy(xpath = "//a[@id='header-hemen-tab']")
    public WebElement migrosHemenElementi;

    @FindBy(xpath = "//*[text()='Tümünü Reddet']")
    public WebElement cerezReddetElementi;

    @FindBy(xpath = "//*[text()='Üye Ol veya Giriş Yap']")
    public WebElement uyeOlElementi;

    @FindBy(xpath = "(//span[@class='subtitle-2 text-color-black' and text()='Et & Tavuk & Balık']")
    public WebElement hiddenElement;

    public String getHiddenElementText() {
        return hiddenElement.getText();
    }

    @FindBy(xpath = "//sm-list-page-item/*")
    public List <WebElement> urunlerElementListesi;

    @FindBy(xpath = "//div[normalize-space()='Sepete Ekle']")
    public WebElement sepeteEkleElementi;



}
