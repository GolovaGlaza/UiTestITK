package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SignInPage extends AbsBasePage {

    public SignInPage(WebDriver driver){
        super(driver);
    }

    private String username = System.getProperty("username");
    private String password = System.getProperty("password");

    @FindBy(id = "username")
    WebElement loginField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(xpath = "//button[text()= 'Вход']")
    WebElement authButton;

    @FindBy(xpath = "//div[text()= 'Алексей О']")
    WebElement profileName;

    public void login(){
        waiter.waitForCondition(ExpectedConditions.visibilityOf(loginField));
        loginField.sendKeys(username);
        waiter.waitForCondition(ExpectedConditions.visibilityOf(passwordField));
        passwordField.sendKeys(password);
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(authButton));
        authButton.click();
    }

    public boolean isLoginConfirmed(){
        return waiter.waitForCondition(ExpectedConditions.visibilityOf(profileName));
    }
}
