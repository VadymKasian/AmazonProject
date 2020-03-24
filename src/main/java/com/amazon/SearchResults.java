package com.amazon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResults {
    WebDriver driver;

    @FindBy(xpath = "//span[contains(@class,'a-size-base a-color-base') and contains(text(),'Books')]")
    private WebElement booksFilter;

    public SearchResults(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebElement getBooksFilter() {
        return booksFilter;
    }
}
