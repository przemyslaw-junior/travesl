package HomeWork;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class FormSingUp {

    @Test
    public void emptySingUpForm(){

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");

        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
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

        List<String> requiredFields = driver.findElements(By.xpath("//div[@class='alert alert-danger' ]//p"))
                .stream()
                .map(element -> element.getAttribute("textContent"))
                .collect(Collectors.toList());

        requiredFields.forEach(System.out::println);

        Assert.assertEquals("The Email field is required.", requiredFields.get(0));
        Assert.assertEquals("The Password field is required.", requiredFields.get(1));
        Assert.assertEquals("The Password field is required.", requiredFields.get(2));
        Assert.assertEquals("The First name field is required.", requiredFields.get(3));
        Assert.assertEquals("The Last Name field is required.", requiredFields.get(4));

    }

    @Test
    public void notValidEmailSingUpForm(){

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");

        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
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
                .collect(Collectors.toList());
        Assert.assertTrue(requiredField.contains("The Email field must contain a valid email address."));
    }
}
