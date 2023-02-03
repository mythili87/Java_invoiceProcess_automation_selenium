package com.timesheet.processes.netrovert;

import com.timesheet.processes.config.Config;
import com.timesheet.processes.netrovert.model.TimeSheetModel;
import com.timesheet.processes.netrovert.pages.LoginPage;
import com.timesheet.processes.netrovert.pages.TimeCardDetailsPage;
import com.timesheet.processes.utils.LocalDrive;
import com.timesheet.processes.utils.Utils;
import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Netrovert {
    private WebDriver driver;
    private Config config;
    private static Logger logger = LoggerFactory.getLogger(Netrovert.class);
    private TimeSheetModel timeSheetModel = new TimeSheetModel();

    public Netrovert(WebDriver driver) {

        this.driver = driver;
        Yaml yaml = new Yaml();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("netrovertProperties.yaml");
        config = yaml.loadAs(inputStream,Config.class);
        driver.get(config.getUrl());

    }
    public void logIn() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.setUserName(config.getUserName());
        loginPage.setPassword(config.getPassword());
        loginPage.clickLogInBtn();

    }
    public void grabTimeSheet() throws Exception {

        TimeCardDetailsPage timeCardDetailsPage = new TimeCardDetailsPage(driver);
        timeCardDetailsPage.clickTimeCardDtlTab();
        String folderName = LocalDrive.createFolder();
        timeCardDetailsPage.loadTimeCardInfo(timeSheetModel);
        String formattedDate = LocalDate.parse(timeSheetModel.getEndDate(), DateTimeFormatter.ofPattern("M/d/yyyy")).format(DateTimeFormatter.ofPattern("MMddyyyy"));
        String path = "C:\\Mythili\\Timesheets&Invoices\\Netrovert\\Timesheets\\"+folderName+"\\Netrovert_ts_we_"+formattedDate+".pdf";

        //copy file path to clipboard
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();
        StringSelection strSel = new StringSelection(path);
        clipboard.setContents(strSel, null);

        if (Utils.contains(timeSheetModel.getStatus(), new String[] {"Approved for Billing", "Invoiced"})){

            timeCardDetailsPage.clickTimeCardID();
            timeCardDetailsPage.clickPrintableView();

            //switch to printable view tab
            Utils.switchTabs(1, driver);

            timeCardDetailsPage.clickPrintThisPage();

            //Switch to print dialog box
            Utils.switchToActiveWindow(driver);

           Utils.printTimeSheet(driver);
           Utils.saveAsDialogBox();

            System.out.println("\n\n\n\nNetrovert Timesheet for week ending " + timeSheetModel.getEndDate() + " has been saved!\n\n\n\n");

            //switching to printable view tab
            Utils.switchTabs(1, driver );
            timeCardDetailsPage.clickClosePrintableViewTab();

            //switching to time sheet tab
            Utils.switchTabs(0, driver);

        }  else {

            System.out.println("Netrovert Timesheet for week ending " + timeSheetModel.getEndDate() + " is NOT approved yet!");
        }

        timeCardDetailsPage.clickLogOut();
        System.out.println("\n\n\n\n\n****Logged out successfully from Netrovert!!!****\n\n\n\n\n");

    }

}
