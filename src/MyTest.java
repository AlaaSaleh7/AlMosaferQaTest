import java.time.Duration;
import java.time.LocalDate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTest {
	WebDriver driver = new ChromeDriver();
	String myWebsite = "https://www.almosafer.com/en";

	@BeforeTest
	public void mySetUp() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get(myWebsite);
		WebElement buttonForTheCurrency = driver
				.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary"));
		buttonForTheCurrency.click();
	}

	@Test(priority = 1)
	public void checkTheEnglishLangugeDefult() {
		String actualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
		String expectedLanguage = "en";

		Assert.assertEquals(actualLanguage, expectedLanguage);

	}

	@Test(priority = 2)
	public void checkTheDefultCurrancySAR() {
		String actualCurrency = driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']"))
				.getText();
		String expectedCurrency = "SAR";

		Assert.assertEquals(actualCurrency, expectedCurrency);
	}

	@Test(priority = 3)
	public void checkContactNumber() {

		String actualNumber = driver.findElement(By.cssSelector(".sc-hUfwpO.bWcsTG")).getText();
		String expectedNumber = "+966554400000";

		Assert.assertEquals(actualNumber, expectedNumber);
	}

	@Test(priority = 4)
	public void checkQtafLogoIsInTheFooter() {

		boolean actualLogo = driver.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ciodno.lkfeIG")).isDisplayed();
		boolean expectedLogo = true;

		Assert.assertEquals(actualLogo, expectedLogo);
	}

	@Test(priority = 5)
	public void checkHoteltabIsNotSelected() {
		String actualHotel = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"))
				.getAttribute("aria-selected");
		String expectedHotel = "false";

		Assert.assertEquals(actualHotel, expectedHotel);
	}

	@Test(priority = 6)
	public void checkDepatuerDate() {
		int today = LocalDate.now().getDayOfMonth();
		int tomorrow = LocalDate.now().plusDays(1).getDayOfMonth();

		String actualDepatuer = driver
				.findElement(By.cssSelector("div[class='sc-OxbzP sc-lnrBVv gKbptE'] span[class='sc-fvLVrH hNjEjT']"))
				.getText();
		String expectedDepatuer = Integer.toString(tomorrow);

		Assert.assertEquals(actualDepatuer, expectedDepatuer);
	}

	@Test(priority = 7)
	public void checkReturnDate() {
		int today = LocalDate.now().getDayOfMonth();
		int dayAfterTomorrow = LocalDate.now().plusDays(2).getDayOfMonth();

		String actualReturn = driver
				.findElement(By.cssSelector("div[class='sc-OxbzP sc-bYnzgO bojUIa'] span[class='sc-fvLVrH hNjEjT']"))
				.getText();
		String expectedReturn = Integer.toString(dayAfterTomorrow);

		Assert.assertEquals(actualReturn, expectedReturn);
	}
}
