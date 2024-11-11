package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.AbsCommon;



public class CoursePage extends AbsCommon {

    public CoursePage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//a[text()= 'Курсы']")
    WebElement courseButton;

    @FindBy(xpath = "//button[text()= '+ Добавить']")
    WebElement addCourseButton;

    @FindBy(xpath = "(//input[@class = 'form-control '])[1]")
    WebElement courseNameField;

    @FindBy(xpath = "//button[text()='+ Модуль ']")
    WebElement addNewModule;

    @FindBy(xpath = "//input[@placeholder = 'Модуль']")
    WebElement moduleField;

    @FindBy(xpath = "//button[text()= 'Create']")
    WebElement createCourse;

    public void createNewCourse(String lastModuleId, String courseName){
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(courseButton));
        courseButton.click();
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(addCourseButton));
        addCourseButton.click();
        waiter.waitForCondition(ExpectedConditions.visibilityOf(courseNameField));
        courseNameField.sendKeys(courseName);
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(addNewModule));
        addNewModule.click();
        waiter.waitForCondition(ExpectedConditions.visibilityOf(moduleField));
        moduleField.sendKeys(lastModuleId);
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(createCourse));
        createCourse.click();

    }

    public boolean isCoursePresent(String courseName){
        By newCourseLocator = By.xpath("//table/tbody[1]/tr/td[1]/span[text()= '" + courseName + "']");

        return waiter.waitForCondition(ExpectedConditions.visibilityOfElementLocated(newCourseLocator));
    }
}


