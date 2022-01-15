package pl.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HotelSearchPage {

    @FindBy(className = "select2-chosen")
    private WebElement searchHotel;

    @FindBy(xpath = "//div[@id='select2-drop']//input")
    private WebElement searchHotelInput;

    @FindBy(xpath = "//span[@class='select2-match']")
    private WebElement hotelMatch;

    @FindBy(name = "checkin")
    private WebElement checkinInput;

    @FindBy(name = "checkout")
    private WebElement checkoutInput;

    @FindBy(id = "travellersInput")
    private WebElement travellersInput;

    @FindBy(id = "adultPlusBtn")
    private WebElement adultPlusBtn;

    @FindBy(id = "childPlusBtn")
    private WebElement childPlusBtn;

    @FindBy(xpath = "//button[@type='submit' and text()=' Search']")
    private WebElement searchButton;


    public HotelSearchPage(WebDriver driver){
        PageFactory.initElements( driver,this);
    }

    public void setCity(String cityName){
        searchHotel.click();
        searchHotelInput.sendKeys("Dubai");
        hotelMatch.click();
    }

    public void setDates(String checkin, String checkout){
        checkinInput.sendKeys("21/01/2021");
        checkoutInput.sendKeys("24/01/2021");
    }

    public void setTravellers(int adultsToAdd, int childToAdd){
        travellersInput.click();
        addTraveler(adultPlusBtn,adultsToAdd);
        addTraveler(adultPlusBtn,childToAdd);
    }


    public void addTraveler(WebElement travelerBtn, int numberOfTravelers){
        for (int i=0; i< numberOfTravelers; i++){
            travelerBtn.click();
        }
    }

    public void performSearch(){
        searchButton.click();
    }
}
