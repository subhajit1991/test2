package example;

import org.testng.annotations.Test;

import excelLib.ExcelWrite;
import excelLib.excelLib;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
//import utility.Log;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import provider.DataProvider;

public class NewTest {
	private WebDriver driver=null;
	private List<String> temp=new ArrayList<String>();
	DataProvider DataProvider;
	boolean bResult;

	@BeforeTest
	public void beforeTest() throws IOException {

		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("useAutomationExtension", false);
		options.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));    
		//options.addArguments("enable-automation");
		//options.addArguments("--headless");
		//options.addArguments("--window-size=1366,768");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-extensions");
		options.addArguments("--dns-prefetch-disable");
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		options.setExperimentalOption("prefs", prefs);
		//options.addArguments("--disable-gpu");
		options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

		DataProvider dp = new DataProvider();
		System.out.println(dp);
		System.setProperty("webdriver.chrome.driver",dp.getDriverPath());//"C:\\Users\\SR068695\\Downloads\\chromedriver.exe");//dp.getDriverPath());
		//Log.info("DriverPath is found successfully");
		driver = new ChromeDriver(options);
		driver.manage().deleteAllCookies();

		//String baseUrl = "https://login.naukri.com/nLogin/Login.php";
		String baseUrl = dp.getApplicationUrl();
		//Log.info("URL is successfully fetched");
		//String baseUrl1 = "https://www.naukri.com/nlogin/login";
		//if (baseUrl == "https://login.naukri.com/nLogin/Login.php");
		driver.get(baseUrl);
		//Log.info("Browser is successfully launched");

		driver.manage().window().maximize();
		//WebDriverWait myWaitVar= new WebDriverWait(driver, 10);


		driver.manage().timeouts().implicitlyWait(50L, TimeUnit.SECONDS);
		//Log.info("Implicit wait is triggered");

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}

		String filePath=dp.getExcelPath();
		String fileName=dp.getExcelName();
		String sheetName=dp.getSheetName();
		temp = excelLib.readExcel(filePath, fileName, sheetName);
	}


	@Test

	public void testEasy() throws InterruptedException, IOException {	
		driver.findElement(By.id("usernameField")).sendKeys(temp.get(0));
		driver.findElement(By.id("passwordField")).sendKeys(temp.get(1));
		driver.findElement(By.xpath("//button[contains(text(), 'Login')]")).click();
		//driver.findElement(By.partialLinkText("Login")).click();

		driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);

		try {
			WebElement isPresent = driver.findElement(By.xpath("//button[contains(@type, 'button')] [@class='feedbackBtn waves-effect waves-light transparent']"));
			if (isPresent.isDisplayed()){
				isPresent.click();
				bResult = true;
			}
		}
		catch (Exception e){
			WebElement Homepage = driver.findElement(By.xpath("//a[contains (@href,'https://my.naukri.com/Profile/view?id=&orgn=homepage')]"));
			if (Homepage.isDisplayed()){
				bResult = true;
			}
			if (bResult) {

				driver.findElement(By.xpath("//div[@class='user-name roboto-bold-text'][contains(text(),'Subhajit Roy')]")).click();

				//				JavascriptExecutor jsx = (JavascriptExecutor)driver;
				//				jsx.executeScript("window.scrollBy(0,250)", "");

				WebElement ResumeHeadline = driver.findElement(By.xpath("//span[contains(@class,'widgetTitle')][contains(text(),'Resume Headline')]"));
				WebElement EditResume = ResumeHeadline.findElement(By.xpath("//span[contains(@class,'edit icon')]  [contains(text(),'Edit')]"));

				//				WebDriverWait wait = new WebDriverWait(driver, 50);
				//				wait.until(ExpectedConditions.invisibilityOf(EditResume));
				EditResume.click();

				WebElement Headline= driver.findElement(By.id("resumeHeadlineTxt"));
				Headline.clear();
				Headline.sendKeys("MCA, Currently Automation Test Engineer");

				driver.findElement(By.xpath("//button[contains(@type,'submit')] [@class='waves-effect waves-light btn-large blue-btn'] [contains(text(),'Save')]")).click();

				//driver.findElement(By.xpath("//a[contains (@href,'https://login.naukri.com/nLogin/Logout.php')]")).click();
				driver.navigate().refresh();

				//driver.get("https://www.skiutah.com/");
				WebElement mynaukri = driver.findElement(By.xpath("//div[contains (@class,'mTxt') and text()='My Naukri']"));
				Actions builder = new Actions(driver);
				builder.moveToElement(mynaukri).build().perform();	
				Thread.sleep(5000);
				//WebElement beginner = driver.findElement(By.partialLinkText("Logout"));
				WebDriverWait wait1=new WebDriverWait(driver, 50L);
				WebElement wd=driver.findElement(By.partialLinkText("Logout"));

				System.out.println("check");
				wd.click();
				//WebElement wd= wait1.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("Logout")));
				//WebElement wd= wait1.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("Logout")));
				System.out.println("check");
				System.out.println(driver.getTitle());

				//driver.close();
				/*
      if (baseUrl1 == "https://www.naukri.com/nlogin/login");
		driver.get(baseUrl1);

		{
			WebElement username1= driver.findElement(By.id("usernameField"));
		      username1.sendKeys("neelback4more@gmail.com");
		      //driver.findElement(By.id("usernameField")).click();

		      WebElement password1= driver.findElement(By.id("passwordField"));
		      password1.sendKeys("Subho1991*");
		      //driver.findElement(By.id("passwordField")).click();

		      driver.findElement(By.xpath("//button[@value='Login']")).click();

		      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		      try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
				}
		}
				 */
			}
		}
	}

	@AfterTest
	public void afterTest() throws IOException, InterruptedException {
		Thread.sleep(5000);
		System.out.println(driver.getTitle());
		String expectedTitle = "Naukri India | Logout";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
		driver.manage().deleteAllCookies();
		driver.close();
		//driver.quit();

		String filePath="C:\\Users\\SR068695\\test2\\Naukri Login.xlsx";
		String fileName="Naukri Login.xlsx";
		String sheet="Login";
		String arr[]={"Status","Successfully Completed"};
		ExcelWrite.writeExcel(filePath, fileName, sheet, arr);
	}


}
