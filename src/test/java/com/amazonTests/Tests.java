package com.amazonTests;

import com.amazon.BookInformationPage;
import com.amazon.FilteredSearchResults;
import com.amazon.MainPage;
import com.amazon.SearchResults;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;

public class Tests {
    WebDriver driver;

    MainPage mainPage;
    SearchResults searchResults;
    FilteredSearchResults filteredSearchResults;
    BookInformationPage bookInformationPage;

    /*
    @  0 - bestseller ("true" \ "false")
    @  1 - name
    @  2 - author
    */
    List<String> bookInformation = new ArrayList<String>();

    List<List<String>> library = new ArrayList<List<String>>();

    @BeforeTest
    public void init(){
        System.setProperty("webdriver.chrome.driver","D:/java/programs/chromedriver.exe");
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        searchResults = new SearchResults(driver);
        filteredSearchResults = new FilteredSearchResults(driver);
        bookInformationPage = new BookInformationPage(driver);
    }

    /*
    @   1. Открывает сайт: https://www.amazon.com
    @   2. В поисковую строку вводит: Java. Применяет фильтр: Books
    @   3. Собирает информацию (название, автор, является ли Best Saler)
    @   о книгах в результатах поиска и сохраняет в колекцию.
    */
    @Test
    public void bookCollection() throws InterruptedException {
        driver.get("https://www.amazon.com");

        mainPage.getSearchBox().sendKeys("Java");
        Thread.sleep(300);
        mainPage.getSearchBox().sendKeys(Keys.ENTER);

        searchResults.getBooksFilter().click();

        Thread.sleep(600);

        filteredSearchResults.getSearchResult().forEach(element -> {
            bookInformation = new ArrayList<String>();
            // check bestseller
            bookInformation.add(String.valueOf(filteredSearchResults.isBestseller(element)));

            // name
            bookInformation.add(filteredSearchResults.getBookName(element));

            // author(s)
            bookInformation.add(filteredSearchResults.getBookAuthor(element));

            library.add(bookInformation);
        });

        System.out.println("Book list");
        for (List<String> book: library){
            System.out.println(book.toString());
        }

    }

    @Test
    public void checkBookContans(){
        driver.get("https://www.amazon.com/Head-First-Java-Kathy-Sierra/dp/0596009208/ref=sr_1_3");

        String name = bookInformationPage.getBookName();
        boolean contains = false;

        for (List<String> list: library) {
            if(name.equals(list.get(1))){
                contains = true;
                break;
            }
        }

        System.out.println();
        System.out.println("Book - " + name + "\n. Contains: " +contains);
    }

    @AfterTest
    public void closeConnection(){
        driver.close();
    }
}
