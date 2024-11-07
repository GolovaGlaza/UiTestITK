package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class QuizPage extends AbsBasePage{

    public QuizPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//a[text()= 'Квизы']")
    WebElement quizButton;

    @FindBy(xpath = "//button[text()= '+ Добавить']")
    WebElement addQuizButton;

    @FindBy(xpath = "//textarea")
    WebElement quizField;

    @FindBy(xpath = "//button[text()= 'Create']")
    WebElement addQuiz;

    public void createNewQuiz(String newQuiz){
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(quizButton));
        quizButton.click();
        waiter.waitForCondition(ExpectedConditions.elementToBeSelected(addQuizButton));
        addQuizButton.click();
        waiter.waitForCondition(ExpectedConditions.visibilityOf(quizField));
        quizField.sendKeys(newQuiz);
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(addQuiz));
        addQuiz.click();
    }

    public boolean isQuizPresent(String newQuiz){
        By quizLocator = By.xpath("//span[text()= '" + newQuiz + "']");

        return waiter.waitForCondition(ExpectedConditions.visibilityOfElementLocated(quizLocator));
    }
}
