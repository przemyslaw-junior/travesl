package pl.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class SingUpPage {

    @FindBy(name = "firstname")
    private WebElement firstNameInput;

    @FindBy(name = "lastname")
    private WebElement lastNameInput;

    @FindBy(name = "phone")
    private WebElement phoneInput;

    @FindBy(name = "email")
    private WebElement emailInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(name = "confirmpassword")
    private WebElement confirmpasswordInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement singUpButton;

    @FindBy(xpath = "//div[@class='alert alert-danger' ]//p")
    private List<WebElement> ereors;

    public SingUpPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void setFirstName(String firstName) {
        firstNameInput.sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        lastNameInput.sendKeys(lastName);
    }

    public void setPhone(String phone) {
        phoneInput.sendKeys(phone);
    }

    public void setEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void setPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void setConfirmpassword(String password) {
        confirmpasswordInput.sendKeys(password);
    }

    public void singUp() {
        singUpButton.click();
    }

    public List<String> getEreors() {
        return ereors.stream()
                .map(element -> element.getAttribute("textContent"))
                .collect(Collectors.toList());
    }
}
