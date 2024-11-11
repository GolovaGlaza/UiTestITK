package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

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


    @FindBy(xpath = "//tbody[1]/tr/td[1]")
    WebElement firstInterview;

    @FindBy(xpath = "//input[@placeholder= 'Название']")
    WebElement interviewNameRedactField;

    @FindBy(xpath = "//input[@type='text']")
    WebElement interviewDateRedactField;

    @FindBy(xpath = "//select[@class='form-control']")
    WebElement interviewTypeRedactSelect;

    @FindBy(xpath = "//textarea[@placeholder= 'Собственная оценка интервью']")
    WebElement interviewGradeRedactField;

    @FindBy(xpath = "//input[@placeholder= 'Видео ссылка']")
    WebElement interviewLinkRedactField;

    @FindBy(xpath = "(//button[text()='Сохранить'])[1]")
    WebElement interviewSaveButton;


    public void createNewInterview(String newInterview){

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

    public void clickInterviewButton(){
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(interviewButton));
        interviewButton.click();
    }

    public void selectInterviewType(String type){
        Select select = new Select(interviewTypeRedactSelect);

        if (type == null || type.equalsIgnoreCase("")) {
            select.selectByVisibleText("");
        }else if (type.equalsIgnoreCase("hr")) {
            select.selectByVisibleText("HR");
        }else if(type.equalsIgnoreCase("tech")) {
            select.selectByVisibleText("tech");
        }
    }

    public void interviewRedaction(String name, String date, String type, String grade, String link){
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(firstInterview));
        firstInterview.click();

        waiter.waitForCondition(ExpectedConditions.visibilityOf(interviewNameRedactField));
        interviewNameRedactField.clear();
        if (name != null) {
            interviewNameRedactField.sendKeys(name);
        }

        waiter.waitForCondition(ExpectedConditions.visibilityOf(interviewDateRedactField));
        interviewDateRedactField.clear();
        if (date != null) {
            interviewDateRedactField.sendKeys(date);
        }

        waiter.waitForCondition(ExpectedConditions.visibilityOf(interviewTypeRedactSelect));
        selectInterviewType(type);

        waiter.waitForCondition(ExpectedConditions.visibilityOf(interviewGradeRedactField));
        interviewGradeRedactField.clear();
        if (grade != null) {
            interviewGradeRedactField.sendKeys(grade);
        }

        waiter.waitForCondition(ExpectedConditions.visibilityOf(interviewLinkRedactField));
        interviewLinkRedactField.clear();
        if (link != null) {
            interviewLinkRedactField.sendKeys(link);
        }

        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(interviewSaveButton));
        interviewSaveButton.click();
    }
}
