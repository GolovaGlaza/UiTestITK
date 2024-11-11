package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.AbsCommon;

public abstract class AbsBasePage extends AbsCommon {

    private String baseUrl;

    public AbsBasePage(WebDriver driver){
        super(driver);
        this.baseUrl = System.getProperty("base.url");
    }

    public void setBaseUrl(String environment){
        switch (environment.toUpperCase()){
            case "VOICETEST":
                this.baseUrl = "https://aqa.javacode.ru/main/";
                break;
            default:
                this.baseUrl = System.getProperty("base.url");
                break;
        }
    }

    public void open(String path){
        path = !path.startsWith("/") ? "/" + path: path;
        driver.get(baseUrl + path);

        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "username")
    WebElement loginField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(xpath = "//button[text()= 'Вход']")
    WebElement authButton;

    private String username = System.getProperty("username");
    private String password = System.getProperty("password");

    public void login(){
        waiter.waitForCondition(ExpectedConditions.visibilityOf(loginField));
        loginField.sendKeys(username);
        waiter.waitForCondition(ExpectedConditions.visibilityOf(passwordField));
        passwordField.sendKeys(password);
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(authButton));
        authButton.click();
    }
}
