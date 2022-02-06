package pl.seleniumdemo.onetime;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.ResultsPage;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class HotelSearch {
    @Test
    public void searchHotelTest() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
        driver.get("http://www.kurs-selenium.pl/demo/");

        driver.findElement(By.xpath("//span[text()='Search by Hotel or City Name']")).click();
        driver.findElement(By.xpath("//div[@id='select2-drop']//input")).sendKeys("Dubai");
        driver.findElement(By.xpath("//span[@class='select2-match' and text()='Dubai']")).click();
        driver.findElement(By.name("checkin")).sendKeys("20/01/2020");
        driver.findElement(By.name("checkout")).sendKeys("22/01/2020");
        driver.findElement(By.id("travellersInput")).click();
        driver.findElement(By.id("adultPlusBtn")).click();
        driver.findElement(By.id("childPlusBtn")).click();
        driver.findElement(By.xpath("//button[text()=' Search']")).click();

        List<String> hotelNames = driver.findElements(By.xpath("//h4[contains(@class, 'list_title')]//b"))
                .stream()
                .map(element -> element.getAttribute("textContent"))
                .collect(Collectors.toList());
        Assert.assertEquals(hotelNames.get(0), "Jumeirah Beach Hotel");
        Assert.assertEquals(hotelNames.get(1), "Oasis Beach Tower");
        Assert.assertEquals(hotelNames.get(2), "Rose Rayhaan Rotana");
        Assert.assertEquals(hotelNames.get(3), "Hyatt Regency Perth");
        driver.quit();
    }

    @Test
    public void searchHotelWithoutNameTest() {

            WebDriverManager.chromedriver().setup();
            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
            driver.get("http://www.kurs-selenium.pl/demo/");

            driver.findElement(By.name("checkin")).sendKeys("20/01/2020");
            driver.findElement(By.name("checkout")).sendKeys("22/01/2020");
            driver.findElement(By.id("travellersInput")).click();
            driver.findElement(By.id("childPlusBtn")).click();
            driver.findElement(By.xpath("//button[text()=' Search']")).click();

            WebElement noResultHeading = driver.findElement(By.xpath("//div[@class='itemscontainer']//h2"));

            Assert.assertTrue(noResultHeading.isDisplayed());
            Assert.assertEquals(noResultHeading.getText(), "No Results Found");
            driver.quit();

        }
}
