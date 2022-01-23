package pl.seleniumdemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.LoggetUserPage;
import pl.seleniumdemo.pages.SingUpPage;

import java.util.List;


public class SingUpTest extends BaseTest {

    @Test
    public void singUpTest() {

        String lastName = "Nazwisko";
        int randomNumber = (int) (Math.random() * 1000);
        String email = "ImieNazwisko" + randomNumber + "@email.com";

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSingUpForm();

        SingUpPage singUpPage = new SingUpPage(driver);
        singUpPage.setFirstName("Imię");
        singUpPage.setLastName(lastName);
        singUpPage.setPhone("123456789");
        singUpPage.setEmail(email);
        singUpPage.setPassword("haslo123");
        singUpPage.setConfirmpassword("haslo123");
        singUpPage.singUp();

        LoggetUserPage loggetUserPage = new LoggetUserPage(driver);

        Assert.assertTrue(loggetUserPage.getHedingText().contains(lastName));
        Assert.assertEquals(loggetUserPage.getHedingText(), "Hi, Imię Nazwisko");
    }

    @Test
    public void singUpEmptyFormTest() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSingUpForm();

        SingUpPage singUpPage = new SingUpPage(driver);
        singUpPage.singUp();

        List<String> errors = singUpPage.getEreors();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(errors.contains("The Email field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The First name field is required."));
        softAssert.assertTrue(errors.contains("The Last Name field is required."));
        softAssert.assertAll();
    }

    @Test
    public void singUpInvalidEmail() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSingUpForm();

        SingUpPage singUpPage = new SingUpPage(driver);
        singUpPage.setFirstName("Imię");
        singUpPage.setLastName("Nazwisko");
        singUpPage.setPhone("123456789");
        singUpPage.setEmail("email");
        singUpPage.setPassword("haslo123");
        singUpPage.setConfirmpassword("haslo123");
        singUpPage.singUp();

        Assert.assertTrue(singUpPage.getEreors()
                .contains("The Email field must contain a valid email address."));
    }
}