package TestNG;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNGFinal {
	
WebDriver driver;
	
	@BeforeMethod
public void init() {

System.setProperty("webdriver.chrome.driver","driver\\chromedriver.exe");

driver = new ChromeDriver();

driver.manage().deleteAllCookies();

driver.get("https://techfios.com/test/104/");
driver.manage().window().maximize();
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}


@Test
public void AddCategory() throws InterruptedException {
	Thread.sleep(2000);
    WebElement category = driver.findElement(By.name("categorydata"));
    String categoryName = "New Category";
    category.sendKeys("Naziabc2");

   
    WebElement addButton = driver.findElement(By.cssSelector("input[value='Add category']"));
    addButton.click();

  
    WebElement categoryLabel = driver.findElement(By.cssSelector("span:contains('" + categoryName + "')"));
    Assert.assertTrue(categoryLabel.isDisplayed(), "Category is not displayed on the page.");
}


@Test
public void AddDuplicatedCategory()throws InterruptedException {
	Thread.sleep(2000);
	

    
    WebElement categoryInput = driver.findElement(By.name("categorydata"));
    String duplicateCategoryName = "CategoryName";  
    categoryInput.sendKeys("Naziabc2");

   
    WebElement addButton = driver.findElement(By.xpath("//input[@value='Add']"));
    addButton.click();

    
    WebElement errorMessage = driver.findElement(By.id("message"));
    String actualErrorMessage = errorMessage.getText();


    String expectedErrorMessage = "The category you want to add already exists: " + duplicateCategoryName;
    Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message does not match");

  
    categoryInput.clear();
}

@Test
public void MonthDropDown()throws InterruptedException {
	Thread.sleep(2000);  
	
	
	
	WebElement monthDropdown = driver.findElement(By.cssSelector("select[value='due_month']"));

     
     List<WebElement> monthOptions = monthDropdown.findElements(By.name("option"));

   
     String[] expectedMonthNames = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

   
     for (String month : expectedMonthNames) {
         boolean isMonthPresent = false;

         for (WebElement option : monthOptions) {
             if (option.getText().equals(month)) {
                 isMonthPresent = true;
                 break;
             }
         }

         Assert.assertTrue(isMonthPresent, "Month '" + month + "' is not present in the dropdown");
     }
 }

 
}







