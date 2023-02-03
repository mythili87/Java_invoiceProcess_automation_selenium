package com.timesheet.processes.netrovert.locators;

import org.openqa.selenium.By;

public class TimeCardDetailsPageLocators {

    private String timeCardTabXpath = "/html/body/div[1]/table[2]/tbody/tr/td/div/table/tbody/tr/td[2]/div/a";
    private String timeCardStatusXpath = "/html/body/div[2]/table/tbody/tr/td[2]/div[3]/div[1]/div/div[2]/table/tbody/tr[2]/td[5]";
    private String timeCardEndDateXpath = "/html/body/div[2]/table/tbody/tr/td[2]/div[3]/div[1]/div/div[2]/table/tbody/tr[2]/td[4]";
    private String timeCardIdXpath = "/html/body/div[2]/table/tbody/tr/td[2]/div[3]/div[1]/div/div[2]/table/tbody/tr[2]/th/a";
    private String printableViewXpath = "/html/body/div[2]/table/tbody/tr/td[2]/div[1]/div[1]/div[2]/a";
    private String printThisPageXpath = "/html/body/div/div[1]/div[1]/ul/li[2]/a";
    private String shadowHostCss = "print-preview-app";
    private String logOutXpath = "/html/body/div[1]/table[1]/tbody/tr/td[2]/div/div[2]/div/a";
    private String closePrintableViewTabXpath = "//a[@href='javascript:window.close%28%29%3B']";


    public By getTimeCardTab() {
        return By.xpath(timeCardTabXpath);
    }

    public By getTimeCardStatusElmnt() {
        return By.xpath(timeCardStatusXpath);
    }

    public By getTimeCardEndDateElmnt() {
        return By.xpath(timeCardEndDateXpath);
    }

    public By getTimeCardID() {
        return By.xpath(timeCardIdXpath);
    }

    public By getPrintableView() {
        return By.xpath(printableViewXpath);
    }

    public By getPrintThisPage() {
        return By.xpath(printThisPageXpath);
    }

    public By getShadowHostElmnt() { return By.cssSelector(shadowHostCss);
    }

    public By getLogOut() { return By.xpath(logOutXpath);
    }

    public By getClosePrintableViewTab() { return By.xpath(closePrintableViewTabXpath);
    }
}
