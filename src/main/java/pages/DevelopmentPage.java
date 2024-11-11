package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DevelopmentPage extends AbsBasePage{

    public DevelopmentPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//a[@href= '/courses']")
    WebElement coursesButton;

    @FindBy(xpath = "//a[@href= '/courses/1005']")
    WebElement newCourseButton;

    @FindBy(xpath = "//button[text()= 'Проверить знания']")
    WebElement checkKnowledgeButton;

    @FindBy(xpath = "//button[text()= 'Начать запись']")
    WebElement startRecordButton;

    @FindBy(xpath = "//button[text()= 'Завершить запись']")
    WebElement endRecordButton;

    public void voiceRecord(){
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(coursesButton));
        coursesButton.click();
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(newCourseButton));
        newCourseButton.click();
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(checkKnowledgeButton));
        checkKnowledgeButton.click();
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(startRecordButton));
        startRecordButton.click();
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(endRecordButton));
        endRecordButton.click();
    }
}
