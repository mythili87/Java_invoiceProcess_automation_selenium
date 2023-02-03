package com.timesheet.processes.netrovert.locators;

import org.openqa.selenium.By;

public class LoginPageLocators {

    private String userNameID = "username";
    private String passwordID = "password";
    private String logInBtnXpath = "/html/body/table/tbody/tr/td/form/div[3]/table/tbody/tr/td[2]/input";
    public By getUserName() {

        return By.id(userNameID);
    }
    public By getPassword() {

        return By.id(passwordID);
    }
    public By getLogInBtn() {

        return By.xpath(logInBtnXpath);
    }
}
