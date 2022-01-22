package pl.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

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

    @FindBy(xpath = "//div[@class='alert alert-danger' ]//p")
    private List<WebElement> ereors;


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

    public List<String> getEreors() {
        return ereors.stream()
                .map(element -> element.getAttribute("textContent"))
                .collect(Collectors.toList());
    }
}
