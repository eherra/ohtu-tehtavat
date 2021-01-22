package ohtu;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Tester {

    public static void main(String[] args) throws MalformedURLException {
        WebDriver driver = new HtmlUnitDriver();
        driver.get("http://localhost:4567");
        System.out.println(driver.getPageSource());

        sleep(2);
            // väärän salasanan syöttö
//        WebElement element = driver.findElement(By.linkText("login"));
//        element.click();
//
//        sleep(2);
//        System.out.println(driver.getPageSource());

//        element = driver.findElement(By.name("username"));
//        element.sendKeys("pekka");
//        element = driver.findElement(By.name("password"));
//        element.sendKeys("vaarasalasana");
//        element = driver.findElement(By.name("login"));

        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        sleep(2);
        System.out.println(driver.getPageSource());
        
        Random r = new Random();
        element = driver.findElement(By.name("username"));
        element.sendKeys("testaaja"+ r.nextInt(100000));        
        element = driver.findElement(By.name("password"));
        element.sendKeys("testaajansalaus");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("testaajansalaus");
        element = driver.findElement(By.name("signup"));
        
        sleep(2);
        element.submit();
        System.out.println(driver.getPageSource());

        sleep(3);
        clickLinkWithText("continue to application mainpage", driver);
        System.out.println(driver.getPageSource());

        sleep(2);
        
        clickLinkWithText("logout", driver);
        
        System.out.println(driver.getPageSource());

        driver.quit();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
    
    private static void clickLinkWithText(String text, WebDriver driver) {
        int trials = 0;
        while( trials++<5 ) {
            try{
                WebElement element = driver.findElement(By.linkText(text));
                element.click();
                break;           
            } catch(Exception e) {
                System.out.println(e.getStackTrace());
            }
        }
    }
}
