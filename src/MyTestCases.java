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

	@Test(priority = 1)
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

	@Test(priority = 2)
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

	@Test(priority = 3)
	public void selectTag() {

		WebElement selectElement = driver.findElement(By.id("dropdown-class-example"));
		Select selector = new Select(selectElement);
		int randomIndex = rand.nextInt(1, 4);
		selector.selectByIndex(randomIndex);

	}

	@Test(priority = 4)
	public void checkbox() {
		WebElement checkboxDiv = driver.findElement(By.id("checkbox-example"));
		List<WebElement> myCheackBox = checkboxDiv.findElements(By.xpath("//input[@type='checkbox']"));

		for (int i = 0; i < myCheackBox.size(); i++) {
			myCheackBox.get(i).click();
		}
	}

	@Test(priority = 5)
	public void openWindow() throws InterruptedException {
		driver.findElement(By.id("openwindow")).click();
		List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(windowHandles.get(1));
		Thread.sleep(2000);
		driver.findElement(By.linkText("CONTACT")).click();
		Thread.sleep(3000);
		driver.switchTo().window(windowHandles.get(0));
	}

	@Test(priority = 6)
	public void switchTab() throws InterruptedException {

		driver.findElement(By.id("opentab")).click();
		List<String> windoHandles = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(windoHandles.get(2));
		Thread.sleep(3000);
		
		String ActualValue = driver.getTitle();
		String expectedValue = "Codenbox AutomationLab - YouTube";
		
		org.testng.Assert.assertEquals(ActualValue, expectedValue);
		
	}
	
	@Test(priority = 7)
	public void test() {
		
	}
}
