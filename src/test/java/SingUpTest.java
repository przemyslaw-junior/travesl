import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.Test;


public class SingUpTest {

    @Test
    public void singUp() {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");

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

    // Formularz rejestracyjny
        driver.findElement(By.name("firstname"))
                .sendKeys("Imię");
        driver.findElement(By.name("lastname"))
                .sendKeys("Nazwisko");
        driver.findElement(By.name("phone"))
                .sendKeys("123456789");
        driver.findElement(By.name("email"))
                .sendKeys("ImieNazwisko@email.com");
    }
}