package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import Resource.Base;

public class OneTest extends Base{
public WebDriver driver;
@Test
public void firsttest() throws IOException, InterruptedException {

	System.out.println("First test got executed");
	driver=initializebrowser();
	driver.get("https://tutorialsninja.com/demo/");
	Thread.sleep(3000);
	Assert.assertTrue(false);
	
}
@AfterMethod
public void close() {
	driver.close();
}
}
