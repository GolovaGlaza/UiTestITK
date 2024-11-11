package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SignInPage extends AbsBasePage {

    public SignInPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//div[text()= 'Алексей О']")
    WebElement profileName;


    public boolean isLoginConfirmed(){
        return waiter.waitForCondition(ExpectedConditions.visibilityOf(profileName));
    }
}
