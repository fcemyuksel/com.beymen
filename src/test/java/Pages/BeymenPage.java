package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

import static utilities.Driver.driver;

public class BeymenPage {
    public BeymenPage(){

        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//button[@id='onetrust-accept-btn-handler']")
    public WebElement cerezElementi;


    @FindBy(xpath = "(//*[@class='icon icon-close'])[2]")
    public WebElement cinsiyetElementi;

    @FindBy(xpath = "//a[@title='Hesabım']")
    public WebElement hesabimElementi;

    @FindBy(xpath = "//header[@class='o-header']")
    public WebElement headerElementi;

    @FindBy(id="o-searchSuggestion__input")
    public WebElement searchElementi;

    @FindBy(xpath = "//*[@id=\"productList\"]/div/*")
    public List <WebElement> productList;

    @FindBy(xpath = "//*[@class='o-productDetail__title']")
    public WebElement secilenUrunBilgisi;

    @FindBy (xpath = "//*[@id='priceNew']" )
    public WebElement secilenUrunFiyat;

    @FindBy (xpath = "//span[@class='m-variation__item']")
    public List <WebElement> bedenElementi;

    @FindBy(xpath = "//span[@class='m-variation__item -criticalStock']")
    public List<WebElement> kritikBedenlerListesi;

    @FindBy(xpath ="//button[@id='addBasket']" )
    public WebElement sepeteEkleElementi;

    @FindBy(xpath = "//*[@class='icon icon-cart icon-cart-active']")
    public WebElement sepetimElementi;

    @FindBy (xpath ="//span[@class='m-productPrice__salePrice']" )
    public WebElement sepettekiUrunElementi;

    @FindBy (xpath = "//select[@id='quantitySelect0-key-0']")
    public WebElement dropdownMenu;

    @FindBy (xpath = "//button[@id='removeCartItemBtn0-key-0']" )
    public WebElement silElementi;

    @FindBy (xpath = "//*[text()='Sepetinizde Ürün Bulunmamaktadır']" )
    public WebElement bosSepetElementi;

    }





