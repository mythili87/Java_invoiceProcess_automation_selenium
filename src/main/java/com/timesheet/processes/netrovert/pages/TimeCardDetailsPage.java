package com.timesheet.processes.netrovert.pages;

import com.timesheet.processes.netrovert.locators.TimeCardDetailsPageLocators;
import com.timesheet.processes.netrovert.model.TimeSheetModel;
import com.timesheet.processes.utils.Utils;
import org.openqa.selenium.*;

public class TimeCardDetailsPage extends Page {

    TimeCardDetailsPageLocators timeCardDetailsPageLocators;
    public TimeCardDetailsPage(WebDriver driver) {

        super.driver = driver;
        timeCardDetailsPageLocators = new TimeCardDetailsPageLocators();
    }
    public void clickTimeCardDtlTab() {
        Utils.wait(3);
        driver.findElement(timeCardDetailsPageLocators.getTimeCardTab()).click();
    }
    public String getTimeCardStatus() {

        return driver.findElement(timeCardDetailsPageLocators.getTimeCardStatusElmnt()).getText();
    }
    public String getTimeCardEndDate() {

        return driver.findElement(timeCardDetailsPageLocators.getTimeCardEndDateElmnt()).getText();
    }
    public void clickTimeCardID() {

        driver.findElement(timeCardDetailsPageLocators.getTimeCardID()).click();
    }
    public void clickPrintableView() {

        driver.findElement(timeCardDetailsPageLocators.getPrintableView()).click();
    }
    public void clickPrintThisPage() {
        driver.findElement(timeCardDetailsPageLocators.getPrintThisPage()).click();
    }
    public WebElement getShadowHost() {
        return driver.findElement(timeCardDetailsPageLocators.getShadowHostElmnt());
    }
    public void clickLogOut() {
        driver.findElement(timeCardDetailsPageLocators.getLogOut()).click();
    }
    public void clickClosePrintableViewTab() {
        driver.findElement(timeCardDetailsPageLocators.getClosePrintableViewTab());
    }
    public void loadTimeCardInfo(TimeSheetModel timeSheetModel) {
       timeSheetModel.setStatus(getTimeCardStatus());
       timeSheetModel.setEndDate(getTimeCardEndDate());
    }

}




















