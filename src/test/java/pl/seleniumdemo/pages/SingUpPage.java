package pl.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SingUpPage {

    public SingUpPage (WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    @FindBy(name = "firstname")
    private WebElement firstNameInput;

    @FindBy (name = "lastname")
    private WebElement lastNameInput;

    @FindBy (name = "phone")
    private WebElement phoneInput;

    @FindBy (name = "email")
    private WebElement emailInput;

    @FindBy (name = "password")
    private WebElement passwordInput;

    @FindBy (name = "confirmpassword")
    private WebElement confirmpasswordInput;

    @FindBy (xpath = "//button[@type='submit']")
    private WebElement singUpButton;

    public void setFirstNameInput(String firstName){
        firstNameInput.sendKeys(firstName);
    }

    public void setLastNameInput(String lsatName){
        lastNameInput.sendKeys(lsatName);
    }

    public void setPhoneInput(String phone){
        phoneInput.sendKeys(phone);
    }

    public void setEmailInput(String email){
        emailInput.sendKeys(email);
    }

    public void setPasswordInput(String password){
        passwordInput.sendKeys(password);
    }

    public void setConfirmpasswordInput(String password){
        confirmpasswordInput.sendKeys(password);
    }
    public void singUp(){
        singUpButton.click();
    }
}
/*
 driver.findElement(By.name("firstname"))
         .sendKeys("Imię");
         driver.findElement(By.name("lastname"))
         .sendKeys(lastName);
         driver.findElement(By.name("phone"))
         .sendKeys("123456789");
         driver.findElement(By.name("email"))
         .sendKeys(email);
         driver.findElement(By.name("password"))
         .sendKeys("haslo123");
         driver.findElement(By.name("confirmpassword"))
         .sendKeys("haslo123");
         driver.findElements(By.xpath("//button[@type='submit']"))
         .stream()
         .filter(WebElement::isDisplayed)
         .findFirst()
         .ifPresent(WebElement::click);*/
