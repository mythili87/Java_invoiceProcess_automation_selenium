package com.timesheet.processes.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

public class Utils {

    public static WebDriver invokeBrowser( ) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        return driver;

    }
    public static boolean contains(String inputString, String[] items) {
        return Arrays.stream(items).anyMatch(inputString::contains);
    }

    public static void switchToActiveWindow(WebDriver driver) {
        Set<String> windowHandles = driver.getWindowHandles();
        if (!windowHandles.isEmpty()) {
            driver.switchTo().window((String) windowHandles.toArray()[windowHandles.size() - 1]);
        }
    }

    public static SearchContext getShadowRoot (WebDriver driver, WebElement shadowHost) {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (SearchContext) js.executeScript("return arguments[0].shadowRoot", shadowHost);

    }

    public static void printTimeSheet(WebDriver driver) throws AWTException {

        //locating shadow root element of select drop down
        WebElement shadowHost = driver.findElement(By.cssSelector("print-preview-app"));
        SearchContext shadowroot = Utils.getShadowRoot(driver,shadowHost);
        SearchContext printPreviewSidebar = Utils.getShadowRoot(driver, shadowroot.findElement(By.cssSelector("print-preview-sidebar")));
        SearchContext printPrevDestSet = Utils.getShadowRoot(driver, printPreviewSidebar.findElement(By.cssSelector("print-preview-destination-settings")));
        SearchContext printPrevDestSel = Utils.getShadowRoot(driver, printPrevDestSet.findElement(By.cssSelector("print-preview-destination-select")));

        //selecting Save As PDF from drop down
        wait(3);
        Select select = new Select(printPrevDestSel.findElement(By.cssSelector("select.md-select")));
        select.selectByValue("Save as PDF/local/");

        //locating shadow root element of Save button
        WebElement shadowHost1 = driver.findElement(By.cssSelector("print-preview-app"));
        SearchContext shadowroot1 = Utils.getShadowRoot(driver,shadowHost1);
        SearchContext printPreviewSidebar1 = Utils.getShadowRoot(driver, shadowroot1.findElement(By.cssSelector("print-preview-sidebar")));
        SearchContext printPrevButtonStrip = Utils.getShadowRoot(driver, printPreviewSidebar1.findElement(By.cssSelector("print-preview-button-strip")));

        //click on Save
        wait(3);
        printPrevButtonStrip.findElement(By.cssSelector("cr-button.action-button")).click();
    }

    public static void saveAsDialogBox() throws AWTException {

        //deleting the default filename in input box and pasting the copied file path
        wait(1);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_DELETE);
        robot.keyRelease(KeyEvent.VK_DELETE);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        //click on save
        wait(1);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
    public static void switchTabs(int i, WebDriver driver) {
        ArrayList<String> tabs_windows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs_windows.get(i));

    }

    public static void wait(int sec) {

        try {
            Thread.sleep(sec*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
