package pl.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MyAccountPage {

    @FindBy (xpath= "//li[@id='li_myaccount']")
    private List<WebElement> myAccountLink;

    @FindBy (xpath = "//a[text()='  Sign Up']")
    private List<WebElement> singUpLink;

    private WebDriver driver;

    public MyAccountPage(WebDriver driver) {
        PageFactory.initElements( driver,this);
        this.driver = driver;
    }

    public void openSingUpForm(){
        myAccountLink.stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        singUpLink.get(1).click();
    }
}
