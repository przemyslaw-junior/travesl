import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class HotelSearch {

    @Test
    public void searchHotel() {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");

        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
    // Wpisanie szukanego miasta
        driver.findElement(By.className("select2-chosen")).click();
        driver.findElement(By.xpath("//div[@id='select2-drop']//input")).sendKeys("Dubai");
        driver.findElement(By.xpath("//span[@class='select2-match']")).click();

/*
    // Check in
        // wpisanie ręcznie daty
        driver.findElement(By.xpath("//*[@name='checkin']")).sendKeys("5/01/2022");
/*
    // Check out
        // wpisanie ręcznie daty
        driver.findElement(By.name("checkout")).sendKeys("15/01/2022");
     */
        // wpisanie daty z wyboru kalendarza
        driver.findElement(By.name("checkin")).click();
        driver.findElements(By.xpath("//td[@class='day ' and text()='5']"))
                .stream()
                .filter(element -> element.isDisplayed())
                .findFirst()
                .ifPresent(element -> element.click());

        driver.findElement(By.name("checkout")).click();
        driver.findElements(By.xpath("//td[@class='day ' and text()='8']"))
                .stream()
                .filter(element -> element.isDisplayed())
                .findFirst()
                .ifPresent(element -> element.click());

        // ilość osób
        driver.findElement(By.id("travellersInput")).click();
        driver.findElement(By.id("adultPlusBtn")).click();
        driver.findElement(By.id("childPlusBtn")).click();
    }
}
