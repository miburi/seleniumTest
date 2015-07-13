package org.openqa.selenium.example;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Example  {
    public static void main(String[] args) {
        // Create a new instance of the html unit driver
        // Notice that the remainder of the code relies on the interface,
        // not the implementation.
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\miburi\\IdeaProjects\\chromedriver.exe");
        System.out.println("testing");
        WebDriver driverC = new ChromeDriver();
        //WebDriver driverF = new FirefoxDriver();
        WebDriver driver = new HtmlUnitDriver();

        //git add from PC
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.OFF);
        java.util.logging.Logger.getLogger("org.apache.http").setLevel(java.util.logging.Level.OFF);

        // And now use this to visit Google
        //driver.get("http://www.zillow.com");

        driverC.get("http://www.zillow.com");
        System.out.println("The title from Chrome is " + driverC.getTitle());
        try {
            // Find the text input element by its name
            //the searchbox on zillow
            WebElement searchbox = driverC.findElement(By.id("citystatezip"));
            searchbox.sendKeys("Orlando, Florida");
            WebElement clickBox = driverC.findElement(By.xpath("//button[@type='submit']"));
            clickBox.click();
            String is_active="listing-type selected";
            System.out.println("DEBUG 1");
            long end = System.currentTimeMillis() + 5000;
            WebElement listingsMenu;
            while (System.currentTimeMillis() < end) {
                // Browsers which render content (such as Firefox and IE) return "RenderedWebElements"
                System.out.println("Keep refreshing");
                listingsMenu = driverC.findElement(By.id("listings-menu-label"));
                // If results have been returned, the results are displayed in a drop down.
                if (listingsMenu.isDisplayed()) {
                    listingsMenu.click();
                    break;
                }
            }

            System.out.println("DEBUG 2");

            WebElement fsVar = driverC.findElement(By.xpath(".//*[@id='fs-listings']"));
            WebElement pmVar = driverC.findElement(By.xpath(".//*[@id='pm-listings']"));
            WebElement frVar = driverC.findElement(By.xpath(".//*[@id='fr-listings']"));
            WebElement rsVar = driverC.findElement(By.xpath(".//*[@id='rs-listings']"));

            System.out.println(rsVar.getAttribute("class"));
            if ( fsVar.getAttribute("class").equals(is_active) ){
                System.out.println("FSVAR IS ACTIVE");
            }
            else{
                driverC.findElement(By.xpath("//div/label[@for='fs-listings-input']")).click();
                System.out.println("FSVAR IS NOT ACTIVE, TURN ON");
            }

            if ( pmVar.getAttribute("class").equals(is_active) ){
                System.out.println("PMVAR IS ACTIVE");
            }
            else{
                driverC.findElement(By.xpath("//div/label[@for='pm-listings-input']")).click();
                System.out.println("PMVAR IS NOT ACTIVE, TURN ON");
            }

            if ( frVar.getAttribute("class").equals(is_active) ){
                System.out.println("FRVAR IS ACTIVE");
            }
            else{
                driverC.findElement(By.xpath("//div/label[@for='fr-listings-input']")).click();
                System.out.println("FRVAR IS NOT ACTIVE, TURN ON");
            }

            if ( rsVar.getAttribute("class").equals(is_active) ){
                System.out.println("RSVAR IS ACTIVE");
            }
            else{
                System.out.println("RSVAR IS NOT ACTIVE, TURN ON");
                WebElement rsButton = driverC.findElement(By.xpath("//div/label[@for='rs-listings-input']"));
                System.out.println("GET TEXT=" + rsButton.getText());
                System.out.println(rsButton.toString());
                rsButton.click();
            }

            Thread.sleep(5000);
        }catch(InterruptedException e)
        {
            System.out.println("Exception" + e);
        }
        driverC.quit();
        driver.quit();
    }
}
