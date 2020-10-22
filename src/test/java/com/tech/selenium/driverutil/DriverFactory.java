package com.tech.selenium.driverutil;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sridhar Bandi on 5/9/18.
 */
public class DriverFactory {

    public static WebDriver getBrowser() throws MalformedURLException {

        WebDriver _driver = null;
        String _browserName = System.getProperty("browser", DriverType.CHROME.toString()).toUpperCase();
        DriverType _driverType = DriverType.valueOf(_browserName);

        switch (_driverType) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                _driver = new ChromeDriver();
                break;
            case CHROME_REMOTE:
                DesiredCapabilities chrome = new DesiredCapabilities();
                chrome.setCapability("browserName", "chrome");
                chrome.setCapability("applicationName","dev-test2");
                _driver = new RemoteWebDriver(new URL("http://xm031w07:4444/wd/hub"), chrome);
                break;
            case FIREFOX_REMOTE:
                DesiredCapabilities firefox = new DesiredCapabilities();
                firefox.setCapability("browserName", "firefox");
                firefox.setCapability("applicationName","dev-test2");
                _driver = new RemoteWebDriver(new URL("http://xm031w07:4444/wd/hub"), firefox);
                break;
            case FIREFOX:
                FirefoxProfile profile = new FirefoxProfile();
                profile.setPreference("network.automatic-ntlm-auth.trusted-uris", "tools.sarenza.laboa");
                WebDriverManager.firefoxdriver().setup();
                _driver = new FirefoxDriver();
                break;
            case EDGE:
                WebDriverManager.edgedriver().setup();
                _driver = new EdgeDriver();
                break;
            case IE:
                WebDriverManager.iedriver().setup();
                _driver = new InternetExplorerDriver();
                break;
            case OPERA:
                WebDriverManager.operadriver().setup();
                _driver = new OperaDriver();
                break;
            default:
                WebDriverManager.chromedriver().setup();
                _driver = new ChromeDriver();
                break;
        }
        _driver.manage().window().maximize();
        _driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return _driver;
    }
}
