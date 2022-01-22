package pl.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.seleniumdemo.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class SingUpPage {

    public SingUpPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

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


    public void setFirstNameInput(String firstName) {
        firstNameInput.sendKeys(firstName);
    }

    public void setLastNameInput(String lastName) {
        lastNameInput.sendKeys(lastName);
    }

    public void setPhoneInput(String phone) {
        phoneInput.sendKeys(phone);
    }

    public void setEmailInput(String email) {
        emailInput.sendKeys(email);
    }

    public void setPasswordInput(String password) {
        passwordInput.sendKeys(password);
    }

    public void setConfirmpasswordInput(String password) {
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

    public void fillSingUpForm(String firstName, String lastName, String phone, String email, String password) {

        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        phoneInput.sendKeys(phone);
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        confirmpasswordInput.sendKeys(password);
        singUpButton.click();
    }

    public void fillSingUpForm2(User user) {

        firstNameInput.sendKeys(user.getFirstName());
        lastNameInput.sendKeys(user.getLastName());
        phoneInput.sendKeys(user.getPhone());
        emailInput.sendKeys(user.getEmail());
        passwordInput.sendKeys(user.getPassword());
        confirmpasswordInput.sendKeys(user.getPassword());
        singUpButton.click();
    }
}
