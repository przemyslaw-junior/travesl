package pl.seleniumdemo.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.seleniumdemo.utils.SeleniumHelper;

import java.util.List;

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

    @FindBy(xpath = "//li[@id='li_myaccount']")
    private List<WebElement> myAccountLink;

    @FindBy(xpath = "//a[text()='  Sign Up']")
    private List<WebElement> singUpLink;


    private WebDriver driver;

    private static final Logger logger = LogManager.getLogger();


    public HotelSearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public HotelSearchPage setCity(String cityName) {
        logger.info("Setting city "+ cityName);
        searchHotel.click();
        searchHotelInput.sendKeys(cityName);
        String xpath = String.format("//span[@class='select2-match' and text()='%s']", cityName);
        SeleniumHelper.waitForElementToExist(driver,By.xpath(xpath));
        driver.findElement(By.xpath(xpath)).click();
        logger.info("Setting city done");
        return this;
    }

    public HotelSearchPage setDates(String checkin, String checkout) {
        logger.info("Setting dates check-in: "+ checkin + " check-out: "+ checkout);
        checkinInput.sendKeys("21/01/2021");
        checkoutInput.sendKeys("24/01/2021");
        logger.info("Setting datas done");
        return this;
    }

    public HotelSearchPage setTravellers(int adultsToAdd, int childToAdd) {
        logger.info("Adding adults: "+ adultsToAdd + " and kids: "+ childToAdd );
        travellersInput.click();
        addTraveler(adultPlusBtn, adultsToAdd);
        addTraveler(childPlusBtn, childToAdd);
        logger.info("Adding travellers done");
        return this;
    }

    public void addTraveler(WebElement travelerBtn, int numberOfTravelers) {
        for (int i = 0; i < numberOfTravelers; i++) {
            travelerBtn.click();
        }
    }

    public ResultsPage performSearch() {
        logger.info("Performing search");
        searchButton.click();
        logger.info("Performing search done");
        return new ResultsPage(driver);
    }

    public void openSingUpForm() {
        myAccountLink.stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        singUpLink.get(1).click();
    }
}
