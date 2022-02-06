package pl.seleniumdemo.onetime;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class SingUp {
    @Test
    public void singUp() {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);

        String lastName= "Nazwisko";
        int randomNumber = (int)(Math.random()*1000);
        String email = "Imie" + randomNumber + "@gmail.com";

        driver.findElements(By.xpath("//li[@id='li_myaccount']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()='  Sign Up']"))
                .get(1)
                .click();
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
        driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();

        WebElement heading = driver.findElement(By.xpath("//h3[@class='RTL']"));

        Assert.assertTrue(heading.getText().contains(lastName));
        Assert.assertEquals(heading.getText(),"Hi, Imię Nazwisko");
    }
    @Test
    public void singUpEmptyForm(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);

        driver.findElements(By.xpath("//li[@id='li_myaccount']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()='  Sign Up']"))
                .get(1)
                .click();
/*
        List<String> errors = driver.findElements(By.xpath("//div[@class='alert alert-danger']"))
                .stream()
                .map(element -> element.getAttribute("textContent"))
                .collect(Collectors.toList());

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(errors.contains("The Email field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The First name field is required."));
        softAssert.assertTrue(errors.contains("The Last Name field is required."));
        softAssert.assertAll();*/
    }
}
