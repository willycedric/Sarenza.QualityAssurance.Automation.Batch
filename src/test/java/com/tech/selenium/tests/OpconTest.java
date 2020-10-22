package com.tech.selenium.tests;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.Duration;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.*;
import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.*;
import java.net.URL;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;
import java.util.Set;

public class OpconTest {
    private WebDriverWait  waiter;
    private   WindowsDriver driver;
    private DesiredCapabilities appCapabilities;
    @Before
    public void setUp() throws MalformedURLException {
     appCapabilities = new DesiredCapabilities();
        appCapabilities.setCapability("app", "C:\\Program Files\\OpConxps\\EnterpriseManager x64\\EnterpriseManager.exe");
      driver = new WindowsDriver(new URL("http://127.0.0.1:4723"), appCapabilities);

          waiter = (WebDriverWait) new WebDriverWait(driver,10)
                .pollingEvery(Duration.ofMillis(50))
                .ignoring(StaleElementReferenceException.class);
    }

   // @Test
    public void exampleTest() throws Exception {
      //  "Login "

        waiter.withMessage("Could not click on element located  on page")
                .until(
                        ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("Edit"))
                ).get(2).sendKeys("rootcquse");

        waiter
                .withMessage("Could not click on element located  on page")
                .until(
                        ExpectedConditions.visibilityOfElementLocated(By.name("Connexion"))
                ).click();

        waiter
                .withMessage("Could not click on element located  on page")
                .until(
                        ExpectedConditions.invisibilityOfElementLocated(By.name("Connexion"))
                );

       // "cancel the scheduler message"
      Thread.sleep(10000);
        Set<String> s = driver.getWindowHandles();
        // Now iterate using Iterator
        Iterator<String> I1= s.iterator();
        if(I1.hasNext()){
            String childWindows = I1.next();
            driver.switchTo().window(childWindows);
            waiter
            .withMessage("Could not click on element located  on page")
            .until(
                    ExpectedConditions.visibilityOfElementLocated(By.name("Non"))
            ).click();
            //"Select the matrix link if displayed"
            try{
                waiter
                        .withMessage("Could not click on element located  on page")
                        .until(
                                ExpectedConditions.visibilityOfElementLocated(By.name("Vue Matrix"))
                        ).click();
            }catch(Exception ex){
            }

            //  "Select the JA"
            waiter
                    .withMessage("Could not click on element located  on page")
                    .until(
                            ExpectedConditions.visibilityOfElementLocated(By.name("1_LABO_A_Operation_JA_FULL ( On Hold )"))
                    ).click();

            //  "Select the JA DOMAIN"
            WebElement ja = waiter
                    .withMessage("JA in the lis")
                    .until(
                            ExpectedConditions.visibilityOfElementLocated(By.name("1_LABOA_JobAlim_04_COUK_EU_DK"))
                    );


            // "Right click on force start"
            ja.click();
            Actions action = new Actions(driver);
            action.contextClick(ja).perform();


            appCapabilities = new DesiredCapabilities();
            appCapabilities.setCapability("app", "Root");
            WindowsDriver ds = new WindowsDriver (new URL("http://127.0.0.1:4723"), appCapabilities);
            ds.findElementByName("Force Start (Forcer à Démarrer)").click();



            Thread.sleep(5000);
            Set<String> s2 = driver.getWindowHandles();
            Iterator<String> I2 = s2.iterator();
            if(I2.hasNext()){
                driver.switchTo().window(I2.next());
                //"Enter status change explanation"
                waiter
                        .until(
                                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("Edit"))
                        ).get(1).sendKeys("Test");


                // "Launching(OK) or canceling(Annuler) the JA"
                waiter
                        .until(
                                ExpectedConditions.visibilityOfElementLocated(By.name("Annuler"))
                        ).click();
            }else{
                throw new Exception("no windows handle found");
            }
        }else{
            throw new Exception("no windows handle found");
        }
    }

    @After
    public void tearDown(){
        driver.quit();
    }

}
