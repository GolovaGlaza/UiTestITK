package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class InterviewPage extends AbsBasePage {

    public InterviewPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//a[text()= 'Интервью']")
    WebElement interviewButton;

    @FindBy(xpath = "//button[text()= '+ Добавить']")
    WebElement addInterviewButton;

    @FindBy(xpath = "//input[@class = 'form-control ']")
    WebElement interviewFieldName;

    @FindBy(xpath = "//button[text()= 'Create']")
    WebElement interviewCreateButton;


    public void createNewInterview(String newInterview){

        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(interviewButton));
        interviewButton.click();
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(addInterviewButton));
        addInterviewButton.click();
        waiter.waitForCondition(ExpectedConditions.visibilityOf(interviewFieldName));
        interviewFieldName.sendKeys(newInterview);
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(interviewCreateButton));
        interviewCreateButton.click();
    }

    public boolean isInterviewPresent(String newInterview){
        By interviewLocator = By.xpath("//span[text()= '" + newInterview + "']");

        return waiter.waitForCondition(ExpectedConditions.visibilityOfElementLocated(interviewLocator));
    }
}
