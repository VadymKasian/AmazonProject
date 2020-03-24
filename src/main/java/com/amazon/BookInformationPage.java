package com.amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.lang.StringBuffer;

public class BookInformationPage {
    WebDriver driver;

    @FindBy(xpath = "//span[@class='author notFaded']")
    private List<WebElement> bookAuthor;

    @FindBy(className = "a-size-extra-large")
    private WebElement bookName;

    private boolean bestSeller = false;

    public BookInformationPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;

        if(driver.findElements(By.className("badge-wrapper")).size() > 0){
            bestSeller = true;
        }
    }

    public String getBookName() {
        return bookName.getText();
    }

    public String getBookAuthor() {
        if (bookAuthor.size() == 1) {
            return bookAuthor.get(0).getText().substring(0, bookAuthor.get(0).getText().indexOf(" (Author)"));
        } else {
            StringBuffer str = new StringBuffer();
            bookAuthor.forEach(element -> {
                str.append(element.getText().substring(0, element.getText().indexOf(" (Author)")) + " ");
            });
            return str.toString();
        }
    }

    public boolean isBestSeller() {
        return bestSeller;
    }
}
