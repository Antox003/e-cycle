// Generated by Selenium IDE
package carzone.com.selenium;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class GestisciClientiAdminTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void gestisciClientiAdmin() {
    driver.get("http://localhost:8080/EcommerceApp/");
    driver.manage().window().setSize(new Dimension(1936, 1048));
    driver.findElement(By.cssSelector(".w3-bar-item:nth-child(4) > b")).click();
    driver.findElement(By.id("x1")).click();
    driver.findElement(By.id("x1")).sendKeys("admin");
    driver.findElement(By.id("x2")).click();
    driver.findElement(By.id("x2")).sendKeys("admin");
    driver.findElement(By.name("b1")).click();
    driver.findElement(By.linkText("Gestisci Clienti")).click();
    driver.findElement(By.cssSelector("tr:nth-child(5) img")).click();
    driver.findElement(By.cssSelector("tr:nth-child(4) img")).click();
  }
}
