import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestCases {

	WebDriver driver = new ChromeDriver();
	String myWebsite = "https://codenboxautomationlab.com/practice/";
	Random rand = new Random();

	@BeforeTest
	public void mySetup() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
		driver.get(myWebsite);

	}

	@Test(priority = 1, enabled = false)
	public void RadioButtons() {
		WebElement RadioDiv = driver.findElement(By.id("radio-btn-example"));
		RadioDiv.findElements(By.tagName("input"));

		int RandomIndex = rand.nextInt(RadioDiv.findElements(By.tagName("input")).size());
		WebElement SelectRadio = RadioDiv.findElements(By.tagName("input")).get(RandomIndex);
		SelectRadio.click();

		boolean ActualResult = SelectRadio.isSelected();
		boolean ExpectedResult = true;

		org.testng.Assert.assertEquals(ActualResult, ExpectedResult);

	}

	@Test(priority = 2, enabled = false)
	public void DynamicDropDownList() throws InterruptedException {

		String[] MyRandomCharacter = { "MA", "GH", "SE", "FA", "CR", "RO" };
		int randomIdex = rand.nextInt(MyRandomCharacter.length);
		String MyInput = MyRandomCharacter[randomIdex];

		WebElement autoCompleteInput = driver.findElement(By.id("autocomplete"));
		autoCompleteInput.sendKeys(MyInput);

		Thread.sleep(1000);
		autoCompleteInput.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ENTER));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		String InputData = (String) js.executeScript("return arguments[0].value", autoCompleteInput);

		String UpdateData = InputData.toLowerCase();
		boolean ActualResult = UpdateData.contains(InputData.toLowerCase());

		org.testng.Assert.assertEquals(ActualResult, true);

	}

	@Test(priority = 3, enabled = false)
	public void selectTag() {

		WebElement selectElement = driver.findElement(By.id("dropdown-class-example"));
		Select selector = new Select(selectElement);
		int randomIndex = rand.nextInt(1, 4);
		selector.selectByIndex(randomIndex);

	}

	@Test(priority = 4, enabled = false)
	public void checkbox() {
		WebElement checkboxDiv = driver.findElement(By.id("checkbox-example"));
		List<WebElement> myCheackBox = checkboxDiv.findElements(By.xpath("//input[@type='checkbox']"));

		for (int i = 0; i < myCheackBox.size(); i++) {
			myCheackBox.get(i).click();
		}
	}

	@Test(priority = 5, enabled = true)
	public void openWindow() throws InterruptedException {
		driver.findElement(By.id("openwindow")).click();
		List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(windowHandles.get(1));
		Thread.sleep(2000);
		driver.findElement(By.linkText("CONTACT")).click();
		Thread.sleep(3000);
		driver.switchTo().window(windowHandles.get(0));
		Thread.sleep(3000);

	}

	@Test(priority = 6, enabled = true)
	public void switchTab() throws InterruptedException {

		driver.findElement(By.id("opentab")).click();
		List<String> windoHandles = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(windoHandles.get(2));
		Thread.sleep(3000);
		
		String ActualValue = driver.getTitle();
		String expectedValue = "Codenbox AutomationLab - YouTube";
		
		org.testng.Assert.assertEquals(ActualValue, expectedValue);
		System.out.println( driver.getTitle());
		Thread.sleep(2000);
		driver.switchTo().window(windoHandles.get(0));
		System.out.println( driver.getTitle());


	}
	
	@Test(priority = 7, enabled = false)
	public void switcALERT() throws InterruptedException {
		
		driver.findElement(By.id("alertbtn")).click();
		Thread.sleep(2000);
		driver.switchTo().alert().accept();
		
		driver.findElement(By.id("name")).sendKeys("Mais");
		driver.findElement(By.id("confirmbtn")).click();
		
		String ActualMsg = driver.switchTo().alert().getText();
		String ExpectedMsg = "Hello Mais, Are you sure you want to confirm?";
		
		org.testng.Assert.assertEquals(ActualMsg, ExpectedMsg);
		Thread.sleep(2000);
		driver.switchTo().alert().accept();
	}
	
	@Test(priority = 8, enabled = false)
	public void Table() {
		
		WebElement Table =  driver.findElement(By.id("product"));
		List <WebElement> TableData = Table.findElements(By.tagName("td"));
		for(int i=1 ; i < TableData.size(); i=i+3) {
			System.out.println(TableData.get(i).getText());
		}
	}
	
	@Test(priority = 9, enabled = false)
	public void ElementDisplayed() throws InterruptedException {
		
		WebElement HideBtn = driver.findElement(By.id("hide-textbox"));
		HideBtn.click();
		
		WebElement TextBox = driver.findElement(By.id("displayed-text"));
		
		boolean ActualResult = TextBox.isDisplayed();
		boolean ExpectedResult = false;
		org.testng.Assert.assertEquals(ActualResult, ExpectedResult);
		
		Thread.sleep(2000);
		
		WebElement ShowBtn = driver.findElement(By.id("show-textbox"));
		ShowBtn.click();
		
		boolean ActualResult2 = TextBox.isDisplayed();
		boolean ExpectedResult2 = true;
		org.testng.Assert.assertEquals(ActualResult2, ExpectedResult2);
		
	}
	
	@Test(priority = 10, enabled = false)
	public void Enable_Disable() {
		
		WebElement DisableBtn = driver.findElement(By.id("disabled-button"));
		DisableBtn.click();
		
		WebElement TextBox = driver.findElement(By.id("enabled-example-input"));
		
		boolean ActualResul = TextBox.isEnabled();
		boolean ExpectedResult = false;
		org.testng.Assert.assertEquals(ActualResul, ExpectedResult);
		
		WebElement EnabbleBtn = driver.findElement(By.id("enabled-button"));
		EnabbleBtn.click();
		
		boolean ActualResul2 = TextBox.isEnabled();
		boolean ExpectedResult2 = true;
		org.testng.Assert.assertEquals(ActualResul2, ExpectedResult2);
				
	}
	
	@Test(priority = 11, enabled = true)
	public void Mouse_Hover() {
		
		JavascriptExecutor JS = (JavascriptExecutor) driver;
		JS.executeScript("window.scrollTo(0, 1750)");
		
		Actions action = new Actions(driver);
		
		WebElement mouseHoverBtn = driver.findElement(By.id("mousehover"));
		
		action.moveToElement(mouseHoverBtn).perform();
		driver.findElement(By.linkText("Reload")).click();
		
	}
	
	
	
	
}




