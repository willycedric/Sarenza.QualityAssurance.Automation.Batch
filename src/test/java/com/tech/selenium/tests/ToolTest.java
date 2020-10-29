package com.tech.selenium.tests;

import com.tech.selenium.driverutil.DriverFactory;
import com.tech.selenium.pageobjects.ToolsPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

/**
 * Created by Sridhar Bandi on 5/9/18.
 * Updated on 26/01/19
 */
public class ToolTest {

    private WebDriver driver;
    ToolsPage toolsPage;


    @Before
    public void setUp() throws MalformedURLException {
        driver = DriverFactory.getBrowser();
        toolsPage = new ToolsPage(driver);

    }

    @Test
    public void exampleTest()  throws Exception{

        toolsPage.openURL();
        toolsPage.set_startDate("29/10/2020");
        toolsPage.filter_domain("es");
        toolsPage.filter();
       Assert.assertTrue(toolsPage.get_searchResult_count()>0);
       toolsPage.toogle_lastest_log();
       Assert.assertTrue(toolsPage.get_count_log_steps()==16);
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
