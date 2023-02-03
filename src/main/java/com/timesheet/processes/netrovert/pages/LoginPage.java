package com.timesheet.processes.netrovert.pages;

import com.timesheet.processes.netrovert.locators.LoginPageLocators;
import org.openqa.selenium.WebDriver;


public class LoginPage extends Page {

    LoginPageLocators loginPageLocators;

    public LoginPage(WebDriver driver) {

        super.driver = driver;
        this.loginPageLocators = new LoginPageLocators();
    }
    public void setUserName(String userName) {

        driver.findElement(loginPageLocators.getUserName()).sendKeys(userName);

    }

    public void setPassword(String password) {

        driver.findElement(loginPageLocators.getPassword()).sendKeys(password);

    }

    public void clickLogInBtn() {

        driver.findElement(loginPageLocators.getLogInBtn()).click();
    }

   }
