package pl.seleniumdemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.ResultsPage;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class HotelSearchTest extends BaseBrowserTest {

    @Test
    public void searchHotel(){
//skrócenie kodu po przez Page Object oraz Page Factory
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity("Dubai");
        hotelSearchPage.setDates("21/01/2022", "24/02/2023");
        hotelSearchPage.setTravellers(1,2);
        hotelSearchPage.performSearch();

        ResultsPage resultsPage = new ResultsPage(driver);
        List<String> hotelNames = resultsPage.getHotelNames();
/*
    // Wpisanie szukanego miasta
        driver.findElement(By.className("select2-chosen")).click();
        driver.findElement(By.xpath("//div[@id='select2-drop']//input")).sendKeys("Dubai");
        driver.findElement(By.xpath("//span[@class='select2-match']")).click();


    // Check in
        // wpisanie ręcznie daty
        driver.findElement(By.xpath("//*[@name='checkin']")).sendKeys("5/01/2022");
*//*
        // wpisanie daty z wyboru kalendarza
        driver.findElement(By.name("checkin")).click();
        driver.findElements(By.xpath("//td[@class='day ' and text()='5']"))
                .stream()
                .filter(element -> element.isDisplayed())
                .findFirst()
                .ifPresent(element -> element.click());
*//*
    // Check out
        // wpisanie ręcznie daty
        //driver.findElement(By.name("checkout")).sendKeys("15/01/2022");



        driver.findElement(By.name("checkout")).click();
        driver.findElements(By.xpath("//td[@class='day ' and text()='15']"))
                .stream()
                .filter(element -> element.isDisplayed())   // wyrażenie lambda
                //.filter(WebElement::isDisplayed)        // metoda referencyjna
                .findFirst()
                .ifPresent(element -> element.click());     // wyrażenie lambda
                //.ifPresent(WebElement::click);  // metoda referencyjna

        // ilość osób
        driver.findElement(By.id("travellersInput")).click();
        driver.findElement(By.id("adultPlusBtn")).click();
        driver.findElement(By.id("childPlusBtn")).click();

        // wciśnięcie przyciski search
        driver.findElement(By.xpath("//button[@type='submit' and text()=' Search']")).click();

        // lista dostępnych hoteli
        List<String> hotelNames = driver.findElements(By.xpath("//h4[contains(@class, 'list_title')]//b"))
                                        .stream()
                                        .map(element -> element.getAttribute("textContent"))
                                        .collect(Collectors.toList());

        System.out.println("Liczba dostępnych Hoteli: "+ hotelNames.size());
        hotelNames.forEach(el-> System.out.println(el));   // wyrażenie lambda
        hotelNames.forEach(System.out::println);      // metoda referencyjna
*/
        // sprawdzenie wyświetlanych nazw z oczekiwanymi rezultatami
        Assert.assertEquals("Jumeirah Beach Hotel",hotelNames.get(0));
        Assert.assertEquals("Oasis Beach Tower",hotelNames.get(1));
        Assert.assertEquals("Rose Rayhaan Rotana",hotelNames.get(2));
        Assert.assertEquals("Hyatt Regency Perth",hotelNames.get(3));

    }
    @Test
    public void searchHotelWithoutNameTest(){

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setDates("21/01/2021", "25/01/2021");
        hotelSearchPage.setTravellers(0,1);
        hotelSearchPage.performSearch();

        ResultsPage resultsPage = new ResultsPage(driver);

        Assert.assertTrue(resultsPage.resultHeading.isDisplayed());
        Assert.assertEquals(resultsPage.getHeadingText(), "No Results Found");
    }
}