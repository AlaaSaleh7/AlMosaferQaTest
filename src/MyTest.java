import java.time.Duration;

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
		String actualCurrency = driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']")).getText();
		String expectedCurrency = "SAR";
		
		Assert.assertEquals(actualCurrency, expectedCurrency);
	}
	
	@Test(priority = 3)
	public void checkContactNumber () {
		
	String actualNumber=driver.findElement(By.cssSelector(".sc-hUfwpO.bWcsTG")).getText();
	String expectedNumber = "+966554400000";
	
	Assert.assertEquals(actualNumber, expectedNumber);
	}
	
	@Test(priority = 4)
	public void checkQtafLogoIsInTheFooter() {
		
	boolean actualLogo=	driver.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ciodno.lkfeIG")).isDisplayed();
	boolean expectedLogo = true;
	
	Assert.assertEquals(actualLogo, expectedLogo);
	}
}
