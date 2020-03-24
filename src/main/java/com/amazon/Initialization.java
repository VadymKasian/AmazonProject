package com.amazon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Initialization {
    WebDriver driver;
    MainPage mainPage;
    SearchResults searchResults;
    FilteredSearchResults filteredSearchResults;
    BookInformationPage bookInformationPage;

    public void init(){
        System.setProperty("webdriver.chrome.driver","D:/java/programs/chromedriver.exe");
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        searchResults = new SearchResults(driver);
        filteredSearchResults = new FilteredSearchResults(driver);
        bookInformationPage = new BookInformationPage(driver);
    }

    public void close(){
        driver.close();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public MainPage getMainPage() {
        return mainPage;
    }

    public SearchResults getSearchResults() {
        return searchResults;
    }

    public FilteredSearchResults getFilteredSearchResults() {
        return filteredSearchResults;
    }

    public BookInformationPage getBookInformationPage() {
        return bookInformationPage;
    }

}
