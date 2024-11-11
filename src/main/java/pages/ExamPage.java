package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ExamPage extends AbsBasePage {

    public ExamPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//a[text()= 'Экзамены']")
    WebElement examButton;

    @FindBy(xpath = "//button[text()= '+ Добавить']")
    WebElement addExamButton;

    @FindBy(xpath = "(//input[@placeholder = 'Название'])[1]")
    WebElement examNameField;

    @FindBy(xpath = "(//input[@placeholder='ID для перемещения'])[1]")
    WebElement idField;

    @FindBy(xpath = "(//button[text()='Переместить по Id'])[1]")
    WebElement idMoveButton;

    @FindBy(xpath = "//button[text()= 'Create']")
    WebElement createExamButton;


    public void createNewExam(String newExamName, String lastQuestionId){
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(examButton));
        examButton.click();
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(addExamButton));
        addExamButton.click();
        waiter.waitForCondition(ExpectedConditions.visibilityOf(examNameField));
        examNameField.sendKeys(newExamName);
        waiter.waitForCondition(ExpectedConditions.visibilityOf(idField));
        idField.sendKeys(lastQuestionId);
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(idMoveButton));
        idMoveButton.click();
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(createExamButton));
        createExamButton.click();
    }

    public boolean isExamPresent(String newExamName){
        By examLocator = By.xpath("//span[text()= '" + newExamName + "']");

        return waiter.waitForCondition(ExpectedConditions.visibilityOfElementLocated(examLocator));
    }
}
