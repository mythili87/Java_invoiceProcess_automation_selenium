package com.timesheet.processes.netrovert;

import com.timesheet.processes.utils.Utils;
import org.openqa.selenium.WebDriver;

public class Execute {

    public void execute() throws Exception {

        WebDriver driver = Utils.invokeBrowser();

        Netrovert netrovert = new Netrovert(driver);
        netrovert.logIn();
        netrovert.grabTimeSheet();
        driver.quit();
    }

}
