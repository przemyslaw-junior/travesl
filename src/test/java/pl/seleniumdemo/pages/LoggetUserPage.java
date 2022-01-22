package pl.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoggetUserPage {

    @FindBy(xpath = "//h3[@class='RTL']")
    private WebElement heding;

    public LoggetUserPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public String getHedingText(){
        return heding.getText();
    }
}
