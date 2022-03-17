package pl.seleniumdemo.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.LoggetUserPage;
import pl.seleniumdemo.pages.SingUpPage;
import pl.seleniumdemo.utils.SeleniumHelper;

import java.io.IOException;
import java.util.List;


public class SingUpTest extends BaseTest {

    protected static ExtentTest test;

    @Test
    public void singUpTest() throws IOException {

        String lastName = "Nazwisko";
        int randomNumber = (int) (Math.random() * 1000);
        String email = "ImieNazwisko" + randomNumber + "@email.com";

        test = extentReports.createTest("Open Sing Up Form");
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSingUpForm();

        SingUpPage singUpPage = new SingUpPage(driver);
        singUpPage.setFirstName("Imię");
        test.log(Status.PASS,"Giving the name", SeleniumHelper.getScreenshot(driver));
        singUpPage.setLastName(lastName);
        test.log(Status.PASS,"Giving the last name", SeleniumHelper.getScreenshot(driver));
        singUpPage.setPhone("123456789");
        test.log(Status.PASS,"Set phone number", SeleniumHelper.getScreenshot(driver));
        singUpPage.setEmail(email);
        test.log(Status.PASS,"Set e-mail", SeleniumHelper.getScreenshot(driver));
        singUpPage.setPassword("haslo123");
        test.log(Status.PASS,"Set password", SeleniumHelper.getScreenshot(driver));
        singUpPage.setConfirmpassword("haslo123");
        test.log(Status.PASS,"Confirm password", SeleniumHelper.getScreenshot(driver));
        singUpPage.singUp();
        test.log(Status.PASS,"Click sing up button", SeleniumHelper.getScreenshot(driver));


        LoggetUserPage loggetUserPage = new LoggetUserPage(driver);

        Assert.assertTrue(loggetUserPage.getHedingText().contains(lastName));
        Assert.assertEquals(loggetUserPage.getHedingText(), "Hi, Imię Nazwisko");
        test.log(Status.PASS, "Assertions passed", SeleniumHelper.getScreenshot(driver));
    }

    @Test
    public void singUpEmptyFormTest() throws IOException {

        test = extentReports.createTest("Open Sing Up Form");
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSingUpForm();

        SingUpPage singUpPage = new SingUpPage(driver);
        singUpPage.singUp();
        test.log(Status.PASS,"Click sing up button", SeleniumHelper.getScreenshot(driver));

        List<String> errors = singUpPage.getEreors();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(errors.contains("The Email field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The First name field is required."));
        softAssert.assertTrue(errors.contains("The Last Name field is required."));
        softAssert.assertAll();
        test.log(Status.PASS, "Assertions passed", SeleniumHelper.getScreenshot(driver));
    }

    @Test
    public void singUpInvalidEmail() throws IOException {

        test = extentReports.createTest("Open Sing Up Form");
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSingUpForm();

        SingUpPage singUpPage = new SingUpPage(driver);
        singUpPage.setFirstName("Imię");
        test.log(Status.PASS,"Giving the name", SeleniumHelper.getScreenshot(driver));
        singUpPage.setLastName("Nazwisko");
        test.log(Status.PASS,"Giving the last name", SeleniumHelper.getScreenshot(driver));
        singUpPage.setPhone("123456789");
        test.log(Status.PASS,"Set phone number", SeleniumHelper.getScreenshot(driver));
        singUpPage.setEmail("email");
        test.log(Status.PASS,"Set e-mail", SeleniumHelper.getScreenshot(driver));
        singUpPage.setPassword("haslo123");
        test.log(Status.PASS,"Set password", SeleniumHelper.getScreenshot(driver));
        singUpPage.setConfirmpassword("haslo123");
        test.log(Status.PASS,"Confirm password", SeleniumHelper.getScreenshot(driver));
        singUpPage.singUp();
        test.log(Status.PASS,"Click sing up button", SeleniumHelper.getScreenshot(driver));

        Assert.assertTrue(singUpPage.getEreors()
                .contains("The Email field must contain a valid email address."));
        test.log(Status.PASS, "Assertions passed", SeleniumHelper.getScreenshot(driver));
    }
}