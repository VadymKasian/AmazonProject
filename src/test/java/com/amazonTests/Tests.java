package com.amazonTests;

import com.amazon.ChromeBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Tests {
    WebDriver driver;
    ChromeBrowser chromeBrowser;

    /*
    @  0 - bestseller (true \ false)
    @  1 - name
    @  2 - author
    */
    List<String> bookInformation = new ArrayList<String>();

    List<List<String>> library = new ArrayList<List<String>>();

    @BeforeTest
    public void init(){
        System.setProperty("webdriver.chrome.driver","D:/java/programs/chromedriver.exe");
        driver = new ChromeDriver();
        chromeBrowser = new ChromeBrowser(driver);
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

        WebElement searchBox = chromeBrowser.getSearchBox();

        Thread.sleep(300);
        searchBox.sendKeys("Java");
        searchBox.sendKeys(Keys.ENTER);

        driver.findElement(By
                .xpath("//span[contains(@class,'a-size-base a-color-base') and contains(text(),'Books')]"))
                .click();

        Thread.sleep(600);
        List<WebElement> searchResults = driver.findElements(By.cssSelector("div[class='a-section a-spacing-medium']"));

        searchResults.forEach(element -> {
            bookInformation = new ArrayList<String>();
            // check bestseller
            if(element.findElements(By.xpath(".//div[@class='sg-row']/div/a[@class='a-link-normal']")).size() > 0) {
                bookInformation.add("true");
            } else {
                bookInformation.add("false");
            }

            // name
            bookInformation.add(element.findElement(By
                    .xpath(".//h2/a/span"))
                    .getText());

            // author(s)
            String author = element.findElement(By.xpath(".//div[@class='a-section a-spacing-none']/div[@class='a-row a-size-base a-color-secondary']")).getText();
            if(author.contains("|")){
                author = author.substring(0, author.indexOf("|")-1);
            }
            bookInformation.add(author.substring(3, author.length()));

            library.add(bookInformation);
        });

        System.out.println("Book list");
        for (List<String> book: library){
            System.out.println(book.toString());
        }


        driver.close();
    }

    @Test
    public void checkBookContans(){
        String name = "Head First Java, 2nd Edition";
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
}
