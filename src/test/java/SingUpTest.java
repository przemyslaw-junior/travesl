import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class SingUpTest {

    @Test
    public void singUp() {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);

    // Przycisk [My Account]
        driver.findElements(By.xpath("//li[@id='li_myaccount']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);

    // Przycisk [Sing Up]     -mniej polecana metoda
        driver.findElements(By.xpath("//a[text()='  Sign Up']"))
                .get(1)
                .click();
        String lastName= "Nazwisko";
    // Formularz rejestracyjny
        driver.findElement(By.name("firstname"))
                .sendKeys("Imię");
        driver.findElement(By.name("lastname"))
                .sendKeys(lastName);
        driver.findElement(By.name("phone"))
                .sendKeys("123456789");
        driver.findElement(By.name("email"))
                .sendKeys("ImieNazwisko7@email.com");
        driver.findElement(By.name("password"))
                .sendKeys("haslo123");
        driver.findElement(By.name("confirmpassword"))
                .sendKeys("haslo123");
        driver.findElements(By.xpath("//button[@type='submit']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);

    // sprwadzenie nagłówka
        WebElement heading = driver.findElement(By.xpath("//h3[@class='RTL']"));

        Assert.assertTrue(heading.getText().contains(lastName));
        Assert.assertEquals(heading.getText(),"Hi, Imię Nazwisko");
    }
}