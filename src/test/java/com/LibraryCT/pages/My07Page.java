package com.LibraryCT.pages;

import com.LibraryCT.utility.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class My07Page extends BasePage {

    public WebElement borrowBook(String book) {
        String xpath = "//td[3][.='" + book + "']/../td/a";
        return Driver.getDriver().findElement(By.xpath(xpath));
    }

    @FindBy(xpath = "//tbody//td[2]")
    public List<WebElement> allBorrowedBooksName;
}

