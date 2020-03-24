package com.amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class FilteredSearchResults {
    WebDriver driver;

    @FindBy(xpath = "//div[@class='a-section a-spacing-medium']")
    private List<WebElement> searchResult;

    @FindBy(xpath = ".//h2/a/span")
    private WebElement bookName;

    @FindBy(xpath = ".//div[@class='a-section a-spacing-none']/div[@class='a-row a-size-base a-color-secondary']")
    private WebElement bookAuthor;

    public FilteredSearchResults(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public List<WebElement> getSearchResult(){
        return searchResult;
    }

    public boolean isBestseller(WebElement element){
        if(element.findElements(By.xpath(".//div[@class='sg-row']/div/a[@class='a-link-normal']")).size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public String getBookName(WebElement element) {
        return element.findElement(By.xpath(".//div[@class='sg-col-inner']/div/h2")).getText();
    }

    public String getBookAuthor(WebElement element) {
        String author = element.findElement(By
                .xpath(".//div[@class='sg-col-inner']/div/div[@class='a-row a-size-base a-color-secondary']"))
                .getText();
        if(author.contains(" | ")){
            author = author.substring(0, author.indexOf(" | ")-1);
        }
        author = author.substring(3, author.length());
        return author;
    }
}
