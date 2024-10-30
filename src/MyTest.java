import java.time.Duration;
import java.time.LocalDate;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTest {
	WebDriver driver = new ChromeDriver();
	String myWebsite = "https://www.almosafer.com/en";
	Random rand = new Random();

	@BeforeTest
	public void mySetUp() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get(myWebsite);
		WebElement buttonForTheCurrency = driver
				.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary"));
		buttonForTheCurrency.click();
	}

	@Test(priority = 1, enabled = false)
	public void checkTheEnglishLangugeDefult() {
		String actualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
		String expectedLanguage = "en";

		Assert.assertEquals(actualLanguage, expectedLanguage);

	}

	@Test(priority = 2, enabled = false)
	public void checkTheDefultCurrancySAR() {
		String actualCurrency = driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']"))
				.getText();
		String expectedCurrency = "SAR";

		Assert.assertEquals(actualCurrency, expectedCurrency);
	}

	@Test(priority = 3, enabled = false)
	public void checkContactNumber() {

		String actualNumber = driver.findElement(By.cssSelector(".sc-hUfwpO.bWcsTG")).getText();
		String expectedNumber = "+966554400000";

		Assert.assertEquals(actualNumber, expectedNumber);
	}

	@Test(priority = 4, enabled = false)
	public void checkQtafLogoIsInTheFooter() {

		boolean actualLogo = driver.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ciodno.lkfeIG")).isDisplayed();
		boolean expectedLogo = true;

		Assert.assertEquals(actualLogo, expectedLogo);
	}

	@Test(priority = 5, enabled = false)
	public void checkHoteltabIsNotSelected() {
		String actualHotel = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"))
				.getAttribute("aria-selected");
		String expectedHotel = "false";

		Assert.assertEquals(actualHotel, expectedHotel);
	}

	@Test(priority = 6, enabled = false)
	public void checkDepatuerDate() {
		int today = LocalDate.now().getDayOfMonth();
		int tomorrow = LocalDate.now().plusDays(1).getDayOfMonth();

		String actualDepatuer = driver
				.findElement(By.cssSelector("div[class='sc-OxbzP sc-lnrBVv gKbptE'] span[class='sc-fvLVrH hNjEjT']"))
				.getText();
		String expectedDepatuer = Integer.toString(tomorrow);

		Assert.assertEquals(actualDepatuer, expectedDepatuer);
	}

	@Test(priority = 7, enabled = false)
	public void checkReturnDate() {
		int today = LocalDate.now().getDayOfMonth();
		int dayAfterTomorrow = LocalDate.now().plusDays(2).getDayOfMonth();

		String actualReturn = driver
				.findElement(By.cssSelector("div[class='sc-OxbzP sc-bYnzgO bojUIa'] span[class='sc-fvLVrH hNjEjT']"))
				.getText();
		String expectedReturn = Integer.toString(dayAfterTomorrow);

		Assert.assertEquals(actualReturn, expectedReturn);
	}

	@Test(priority = 8, enabled = false)
	public void randomChangeTheLanguage() throws InterruptedException {
		String[] myWebsites = { "https://www.almosafer.com/en", "https://www.almosafer.com/ar" };
		int randomWebsite = rand.nextInt(myWebsites.length);
		driver.get(myWebsites[randomWebsite]);
		String[] englishCitiesNames = { "Jeddah", "Riyadh", "Dubi" };
		String[] arabicCitiesNames = { "دبي", "جدة" };

		int randomArabicCity = rand.nextInt(arabicCitiesNames.length);
		int randomEnglishCity = rand.nextInt(englishCitiesNames.length);

		WebElement hotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		hotelTab.click();
		WebElement hotelSearchBar = driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input"));

		if (driver.getCurrentUrl().equals("https://www.almosafer.com/ar")) {

			String actualArabicWebsite = driver.findElement(By.tagName("html")).getAttribute("lang");
			String expectedArabicWebsite = "ar";

			Assert.assertEquals(actualArabicWebsite, expectedArabicWebsite);
			hotelSearchBar.sendKeys(arabicCitiesNames[randomArabicCity]);
		} else {
			String actualEnglishWebsite = driver.findElement(By.tagName("html")).getAttribute("lang");
			String expectedEnglishWebsite = "en";

			Assert.assertEquals(actualEnglishWebsite, expectedEnglishWebsite);
			hotelSearchBar.sendKeys(englishCitiesNames[randomEnglishCity]);
		}

		Thread.sleep(2000);

		WebElement CitiesList = driver.findElement(By.cssSelector(".sc-phbroq-4.gGwzVo.AutoComplete__List"));

		WebElement SelectNumerOfVistor = driver.findElement(By.cssSelector(".sc-tln3e3-1.gvrkTi"));

		CitiesList.findElements(By.tagName("li")).get(1).click();

		Select select = new Select(SelectNumerOfVistor);

		int randomVisitorNumber = rand.nextInt(2);

		select.selectByIndex(randomVisitorNumber);
		WebElement SearchButton = driver.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']"));
		SearchButton.click();
		Thread.sleep(35000);
	}
	
	@Test(priority = 9, enabled = false)
	public void checkThatThePageLoaded() {
		WebElement SearchResult = driver.findElement(By.xpath("//span[@data-testid='srp_properties_found']"));
		boolean actualResult = SearchResult.getText().contains("found") ||  SearchResult.getText().contains("مكان");
		boolean expectedResult = true;
		
		Assert.assertEquals(actualResult, expectedResult)
	}
}
