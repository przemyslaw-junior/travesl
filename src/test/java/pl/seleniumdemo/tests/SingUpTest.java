package pl.seleniumdemo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.LoggetUserPage;
import pl.seleniumdemo.pages.MyAccountPage;
import pl.seleniumdemo.pages.SingUpPage;


public class SingUpTest extends BaseBrowserTest {

    @Test
    public void singUp() {

/*    // Przycisk [My Account]
        driver.findElements(By.xpath("//li[@id='li_myaccount']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);

    // Przycisk [Sing Up]     -mniej polecana metoda
        driver.findElements(By.xpath("//a[text()='  Sign Up']"))
                .get(1)
                .click();*/
        MyAccountPage myAccountPage = new MyAccountPage(driver);
        myAccountPage.openSingUpForm();

        String lastName = "Nazwisko";
        int randomNumber = (int) (Math.random()*1000);
        String email = "ImieNazwisko"+ randomNumber+ "@email.com";

        SingUpPage singUpPage = new SingUpPage(driver);
        singUpPage.setFirstNameInput("Imię");
        singUpPage.setLastNameInput(lastName);
        singUpPage.setPhoneInput("123456789");
        singUpPage.setEmailInput(email);
        singUpPage.setPasswordInput("haslo123");
        singUpPage.setConfirmpasswordInput("haslo123");
        singUpPage.singUp();
 /*
        // Formularz rejestracyjny
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
                .ifPresent(WebElement::click);
*/
    // sprwadzenie nagłówka
        //WebElement heading = driver.findElement(By.xpath("//h3[@class='RTL']"));
        LoggetUserPage loggetUserPage = new LoggetUserPage(driver);

        Assert.assertTrue(loggetUserPage.getHedingText().contains(lastName));
        Assert.assertEquals(loggetUserPage.getHedingText(), "Hi, Imię Nazwisko");
        //Assert.assertTrue(heading.getText().contains(lastName));
        //Assert.assertEquals(heading.getText(),"Hi, Imię Nazwisko");

    }
}