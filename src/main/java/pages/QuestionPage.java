package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class QuestionPage extends AbsBasePage {

    public QuestionPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//a[text()= 'Вопросы']")
    WebElement questionButton;

    @FindBy(xpath = "//button[text()= '+ Добавить']")
    WebElement addQuestionButton;

    @FindBy(xpath = "//textarea")
    WebElement questionField;

    @FindBy(xpath = "//button[text()= 'Create']")
    WebElement addQuestion;


    public void createNewQuestion(String newQuestion){
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(questionButton));
        questionButton.click();
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(addQuestionButton));
        addQuestionButton.click();
        waiter.waitForCondition(ExpectedConditions.visibilityOf(questionField));
        questionField.sendKeys(newQuestion);
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(addQuestion));
        addQuestion.click();
    }

    public boolean isQuestionPresent(String newQuestion){
        By questionLocator = By.xpath("(//div[text()= '" + newQuestion + "'])[1]");

        return waiter.waitForCondition(ExpectedConditions.visibilityOfElementLocated(questionLocator));
    }

}
