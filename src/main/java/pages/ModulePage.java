package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class ModulePage extends AbsBasePage {

    public ModulePage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//a[text()= 'Модули']")
    WebElement moduleButton;

    @FindBy(xpath = "//button[text()= '+ Добавить']")
    WebElement addButton;

    @FindBy(xpath = "//input[@placeholder='Название']")
    WebElement nameField;

    @FindBy(xpath = "(//input[@placeholder='ID для перемещения'])[1]")
    WebElement idField;

    @FindBy(xpath = "(//button[text()='Переместить по Id'])[1]")
    WebElement idMoveButton;

    @FindBy(xpath = "//button[text()= 'Create']")
    WebElement addModuleButton;

    @FindBy(xpath = "(//div[contains(@style, 'padding')])[1]/div/child::div[last()]/child::span[2]")
    WebElement lastQuestion;

    @FindBy(xpath = "//table/tbody[1]/tr/child::td[2]")
    WebElement moduleId;

    public void createNewModule(String newModule){
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(moduleButton));
        moduleButton.click();
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(addButton));
        addButton.click();
        waiter.waitForCondition(ExpectedConditions.visibilityOf(nameField));
        nameField.sendKeys(newModule);
        waiter.waitForCondition(ExpectedConditions.visibilityOf(lastQuestion));

        String idText = extractIdText();

        waiter.waitForCondition(ExpectedConditions.visibilityOf(idField));
        idField.sendKeys(idText);
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(idMoveButton));
        idMoveButton.click();
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(addModuleButton));
        addModuleButton.click();
    }

    public String extractIdText() {
        String fullText = lastQuestion.getText();

        return fullText.length() > 5 ? fullText.substring(0, 4) : fullText;
    }


    public boolean isModulePresent(String newModule){
        By moduleLocator = By.xpath("//span[text()= '" + newModule + "']");

        return waiter.waitForCondition(ExpectedConditions.visibilityOfElementLocated(moduleLocator));
    }

    public String getModuleId(){
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(moduleButton));
        moduleButton.click();
        waiter.waitForCondition(ExpectedConditions.visibilityOf(moduleId));
        String lastModuleId = moduleId.getText();
        return lastModuleId;
    }
}
