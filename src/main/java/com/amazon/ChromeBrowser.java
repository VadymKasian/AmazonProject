package com.amazon;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChromeBrowser {
    WebDriver driver;
    @FindBy(id = "twotabsearchtextbox")
    private WebElement searchBox;

    public ChromeBrowser(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebElement getSearchBox() {
        return searchBox;
    }
}
