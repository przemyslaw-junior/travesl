package HomeWork;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class BookingPage {

    @Test
    public void checkInAndOut() throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");

        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//input[@name='checkin']")).click();
        driver.findElement(By.xpath("//*[@name='checkin']")).sendKeys("11/01/2022");
        /*driver.findElements(By.xpath("//td[@class='day ' and text()='11']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);*/

        driver.findElement(By.name("checkout")).click();
        driver.findElements(By.xpath("//td[@class='day ' and text()='15']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);

        driver.findElement(By.id("travellersInput")).click();
        driver.findElement(By.id("childPlusBtn")).click();
        driver.findElement(By.xpath("//button[@type='submit' and text()=' Search']")).click();

        WebElement result = driver.findElement(By.xpath("//h2[@class='text-center']"));
        Assert.assertEquals("No Results Found", result.getText());
    }
}
