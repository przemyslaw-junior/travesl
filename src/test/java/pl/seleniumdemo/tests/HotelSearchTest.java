package pl.seleniumdemo.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.ResultsPage;
import pl.seleniumdemo.utils.ExcelReader;
import pl.seleniumdemo.utils.SeleniumHelper;

import java.io.IOException;
import java.util.List;

public class HotelSearchTest extends BaseTest {

    protected static ExtentTest test;

    @Test
    public void searchHotelTest() throws IOException {

        test = extentReports.createTest("Search Hotel Test");
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity("Dubai");
        test.log(Status.PASS, "Setting city done", SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.setDates("21/01/2022", "24/02/2023");
        test.log(Status.PASS, "Setting dates done", SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.setTravellers(1, 2);
        test.log(Status.PASS, "Setting traveller done", SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.performSearch();
        test.log(Status.PASS, "Performing search done");
        test.log(Status.PASS, "Screenshot", SeleniumHelper.getScreenshot(driver));

        ResultsPage resultsPage = new ResultsPage(driver);
        List<String> hotelNames = resultsPage.getHotelNames();

        Assert.assertEquals(hotelNames.get(0), "Jumeirah Beach Hotel");
        Assert.assertEquals(hotelNames.get(1), "Oasis Beach Tower");
        Assert.assertEquals(hotelNames.get(2), "Rose Rayhaan Rotana");
        Assert.assertEquals(hotelNames.get(3), "Hyatt Regency Perth");
        test.log(Status.PASS, "Assertions passed", SeleniumHelper.getScreenshot(driver));
    }

    @Test
    public void searchHotelWithoutNameTest() throws IOException {
        test = extentReports.createTest("Search Hotel Test Without City");
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        test.log(Status.PASS, "City name skip");
        hotelSearchPage.setDates("21/02/2021", "25/02/2021");
        test.log(Status.PASS, "Setting dates done",SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.setTravellers(0, 1);
        test.log(Status.PASS, "Setting traveller done",SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.performSearch();
        test.log(Status.PASS, "Performing search done",SeleniumHelper.getScreenshot(driver));

        ResultsPage resultsPage = new ResultsPage(driver);

        Assert.assertTrue(resultsPage.resultHeading.isDisplayed());
        Assert.assertEquals(resultsPage.getHeadingText(), "No Results Found");
        test.log(Status.PASS, "Assertion done",SeleniumHelper.getScreenshot(driver));
    }
    @Test (dataProvider = "data")
    public void searchHotelTestWithDataProvider(String city, String hotel) throws IOException {

        test = extentReports.createTest("Search Hotel Test With Data Provider for" + city);
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity(city);
        test.log(Status.PASS, "Setting city done",SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.setDates("21/01/2022", "24/02/2023");
        test.log(Status.PASS, "Setting dates done",SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.setTravellers(1, 2);
        test.log(Status.PASS, "Setting traveller done",SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.performSearch();
        test.log(Status.PASS, "Performing search done",SeleniumHelper.getScreenshot(driver));

        ResultsPage resultsPage = new ResultsPage(driver);
        List<String> hotelNames = resultsPage.getHotelNames();

        Assert.assertEquals(hotelNames.get(0), hotel);
        test.log(Status.PASS, "Assertion done",SeleniumHelper.getScreenshot(driver));
    }
    @DataProvider
    public Object[][] data() throws IOException {
        return ExcelReader.readExcel("testData.xlsx");
    }
}