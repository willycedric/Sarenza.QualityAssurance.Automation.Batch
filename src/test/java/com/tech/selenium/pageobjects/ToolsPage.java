package com.tech.selenium.pageobjects;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;

public class ToolsPage {

    private WebDriver _driver = null;

    @FindBy(name = "q")
    WebElement _searchBox;
    int DELAY = 10;
    private   WebDriverWait  waiter;
    private JavascriptExecutor js;
    public ToolsPage(WebDriver driver) {
        this._driver = driver;
        js = (JavascriptExecutor)_driver;
        PageFactory.initElements(_driver, this);
        waiter = (WebDriverWait) new WebDriverWait(driver, DELAY)
                .pollingEvery(Duration.ofMillis(50))
                .ignoring(StaleElementReferenceException.class);
    }
    @FindBy(id="btnLogFilter")
    public WebElement _btnFilter;
    @FindBy(id="dpLogFilterStartDate")
    private WebElement _startDate;

    @FindBy(id="dpLogFilterEndDate")
    private WebElement _endDate;

    @FindBy(id="ddlLogFilterDomain")
    private  WebElement _domainFilter;
    @FindBy(id="log-table")
    private WebElement _logTableView;

    private By _items = By.className("item");
    private By _toggleIcon = By.className("glyphicon-menu-right");
    private By _logBadge = By.className("badge");
    public void openURL(){
        _driver.get("http://wdongmo:SarMon1005*@tools.sarenza.laboa/JobAlim/Monitoring#jobalim-log");
    }


    public void set_startDate(String date ){
        _startDate.click();
        _startDate.clear();
        _startDate.sendKeys(date);
    }

    public void set_endDate(String date){
       WebElement elt = waiter
        .withMessage(String.format("Trying to fetch elements located by the selector %s", _items))
        .until(ExpectedConditions.visibilityOfElementLocated(By.id("dpLogFilterEndDate")));
        String command = String.format("setTimeout(function(){ arguments[0].value = '%s' ;},1000)", date);
        js.executeScript(command);
        _endDate.click();
        _endDate.clear();
        _endDate.sendKeys(date);
    }

    public void filter(){
        _btnFilter.click();
    }

    public int get_searchResult_count(){
        return  waiter
                .withMessage(String.format("Trying to fetch elements located by the selector %s", _items))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(_items))
                .size();

    }

    public void filter_domain(String domain){
        Select domainSelector = new Select(_domainFilter);
        domainSelector.selectByVisibleText(domain);
    }

    public void toogle_lastest_log(){
     List<WebElement> logs =  waiter
        .withMessage(String.format("Trying to fetch the latest log line with the selector %s", _items))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(_items));
     //Find the last element
     WebElement lastLog = logs.get(logs.size()-1);
     lastLog.findElement(_toggleIcon).click();

    }

    public int get_count_log_steps(){
        return  waiter
                .withMessage(String.format("Trying to fetch elements located by the selector %s", _logBadge))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(_logBadge))
                .size();
    }


}
