package com.LibraryCT.steps;

import com.LibraryCT.pages.BasePage;
import com.LibraryCT.pages.BookPage;
import com.LibraryCT.pages.LoginPage;
import com.LibraryCT.pages.My07Page;
import com.LibraryCT.utility.BrowserUtil;
import com.LibraryCT.utility.DB_Util;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;

import java.util.List;

public class My07StepDefs {
    LoginPage loginPage = new LoginPage();
    BasePage basePage = new BasePage();
    BookPage bookPage = new BookPage();
    My07Page my07Page = new My07Page();

    @Given("the {string} on the home page")
    public void theOnTheHomePage(String username) {
        loginPage.login(username);

    }
    @And("the user navigates to {string} page")
    public void theUserNavigatesToPage(String books) {
        basePage.navigateModule(books);
        BrowserUtil.waitFor(2);
    }
    String MyBorrowingBook;
    @And("the user searches for {string} book")
    public void theUserSearchesForBook(String MyBook) {
        bookPage.search.sendKeys(MyBook+ Keys.ENTER);
        BrowserUtil.waitFor(2);
        MyBorrowingBook = MyBook;
    }
    @When("the user clicks Borrow Book")
    public void theUserClicksBorrowBook() {
        my07Page.borrowBook(MyBorrowingBook).click();
    }
    @Then("verify that book is shown in {string} page")
    public void verifyThatBookIsShownInPage(String arg0) {
        bookPage.navigateModule(arg0);
        Assert.assertTrue(BrowserUtil.getElementsText(my07Page.allBorrowedBooksName).contains(MyBorrowingBook));
    }

    @And("verify logged student has same book in database")
    public void verifyLoggedStudentHasSameBookInDatabase() {
        String query = "select b.name from books b join book_borrow bb on" +
                " b.id = bb.book_id join users u on bb.user_id = u.id where b.name = '"+MyBorrowingBook+"'";
        DB_Util.runQuery(query);

        List<String> actualListFromDb = DB_Util.getColumnDataAsList(1);
        Assert.assertTrue(actualListFromDb.contains(MyBorrowingBook));
    }
}
