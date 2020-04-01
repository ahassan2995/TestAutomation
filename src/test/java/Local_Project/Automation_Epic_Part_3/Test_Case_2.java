package Local_Project.Automation_Epic_Part_3;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class Test_Case_2 {

	WebDriver driver;
	String chrome_driver_directory;
	String driver_name;
	String web_site;

	@Test
	public void test_1() throws InterruptedException {
		
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			final DocumentBuilder builder = factory.newDocumentBuilder();
			try {
				final Document document= builder.parse(new File("Test_Data.xml"));
				document.getDocumentElement().normalize();
				Element root = document.getDocumentElement();
				
				//Test Data
				chrome_driver_directory= root.getElementsByTagName("Chrome_Driver_Directory").item(0).getTextContent();
				driver_name = root.getElementsByTagName("Chrome_Driver_Directory").item(1).getTextContent();
				web_site = root.getElementsByTagName("Web_site").item(0).getTextContent();
				  
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.setProperty(driver_name, chrome_driver_directory);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--lang=en-GB");
		driver = new ChromeDriver(options);
		driver.get(web_site);
		//driver.manage().window().maximize();
		
		String Url = driver.getCurrentUrl().toString();
		Assert.assertTrue(Url.contains("google"));
		Thread.sleep(5000);
		
		WebElement search_field = driver.findElement(By.name("q"));
        search_field.sendKeys("first");
        Thread.sleep(5000);
        
        String keyword = search_field.getAttribute("value");
        Assert.assertEquals(keyword, "first");
        Thread.sleep(5000);
        
        WebElement button = driver.findElement(By.xpath("//input[contains(@aria-label, 'Google Search')]"));
        Assert.assertEquals(button.getAttribute("value"), "Google Search");
        button.sendKeys(Keys.RETURN);
        Thread.sleep(5000);
        
        //driver.quit();
	}
}