package com.LibraryCT.pages;


import com.LibraryCT.utility.ConfigurationReader;
import com.LibraryCT.utility.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {

    public LoginPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "inputEmail")
    public WebElement emailBox;

    @FindBy(id = "inputPassword")
    public WebElement passwordBox;

    @FindBy(tagName = "button")
    public WebElement loginButton;


    public void login(String userType){

        String username= ConfigurationReader.getProperty(userType + "_username");
        String password=ConfigurationReader.getProperty(userType + "_password");

        emailBox.sendKeys(username);
        passwordBox.sendKeys(password);
        loginButton.click();

    }

    public void login(String email,String password){

        emailBox.sendKeys(email);
        passwordBox.sendKeys(password);
        loginButton.click();

    }




}