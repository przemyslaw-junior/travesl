package pl.seleniumdemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.MyAccountPage;
import pl.seleniumdemo.pages.SingUpPage;

import java.util.List;

public class FormSingUpTest extends BaseBrowserTest {
    @Test
    public void emptySingUpForm(){
/*

        //  My Account
        driver.findElements(By.xpath("//li[@id='li_myaccount']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        // Sing Up
        driver.findElements(By.xpath("//a[@class='go-text-right' and text()='  Sign Up']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);

        // End Sign Up
        driver.findElement(By.xpath("//i[@class='fa fa-check-square-o' ]")).click();
*/
        MyAccountPage myAccountPage = new MyAccountPage(driver);
        myAccountPage.openSingUpForm();

        SingUpPage singUpPage = new SingUpPage(driver);
        singUpPage.singUp();

        List<String> requiredFields = singUpPage.getEreors();

        requiredFields.forEach(System.out::println);

        Assert.assertEquals("The Email field is required.", requiredFields.get(0));
        Assert.assertEquals("The Password field is required.", requiredFields.get(1));
        Assert.assertEquals("The Password field is required.", requiredFields.get(2));
        Assert.assertEquals("The First name field is required.", requiredFields.get(3));
        Assert.assertEquals("The Last Name field is required.", requiredFields.get(4));

    }

    @Test
    public void notValidEmailSingUpForm(){


 /*       //  My Account
        driver.findElements(By.xpath("//li[@id='li_myaccount']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        // Sing Up
        driver.findElements(By.xpath("//a[@class='go-text-right' and text()='  Sign Up']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);*/
        MyAccountPage myAccountPage = new MyAccountPage(driver);
        myAccountPage.openSingUpForm();

        SingUpPage singUpPage = new SingUpPage(driver);
        singUpPage.setFirstNameInput("Imię");
        singUpPage.setLastNameInput("Nazwisko");
        singUpPage.setPhoneInput("123456789");
        singUpPage.setEmailInput("email");
        singUpPage.setPasswordInput("haslo123");
        singUpPage.setConfirmpasswordInput("haslo123");
        singUpPage.singUp();

/*
        driver.findElement(By.name("firstname"))
                .sendKeys("Pierwsze Imię");
        driver.findElement(By.name("lastname"))
                .sendKeys("Nazwisko");
        driver.findElement(By.name("phone"))
                .sendKeys("123456789");
        driver.findElement(By.name("email"))
                .sendKeys("email.com");
        driver.findElement(By.name("password"))
                .sendKeys("haslo123");
        driver.findElement(By.name("confirmpassword"))
                .sendKeys("haslo123");
        driver.findElements(By.xpath("//button[@type='submit']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);

        List<String> requiredField =  driver.findElements(By.xpath("//div[@class='alert alert-danger']"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());*/
        Assert.assertTrue(singUpPage.getEreors().contains("The Email field must contain a valid email address."));

    }
}
