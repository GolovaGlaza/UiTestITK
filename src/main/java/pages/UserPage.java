package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UserPage extends AbsBasePage {

    public UserPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//a[text()= 'Пользователи']")
    WebElement usersButton;

    @FindBy(xpath = "//button[text()= '+ Добавить']")
    WebElement addButton;

    @FindBy(xpath = "//small[text() = 'Имя']/following-sibling::input")
    WebElement nameField;

    @FindBy(xpath = "//small[text() = 'Фамилия']/following-sibling::input")
    WebElement surnameField;

    @FindBy(xpath = "//small[text() = 'Эл. почта']/following-sibling::input")
    WebElement emailField;

    @FindBy(xpath = "//small[text() = 'Логин']/following-sibling::input")
    WebElement usernameField;

    @FindBy(xpath = "//small[text() = 'Пароль']/following-sibling::input")
    WebElement passwordField;

    @FindBy(xpath = "//small[text() = 'Роли']/following-sibling::input")
    WebElement roleField;

    @FindBy(xpath = "//button[text()= 'Create']")
    WebElement createUser;


    public void createNewUser(String name, String surname, String email, String username, String password,
                             String role){
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(usersButton));
        usersButton.click();
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(addButton));
        addButton.click();
        waiter.waitForCondition(ExpectedConditions.visibilityOf(nameField));
        nameField.sendKeys(name);
        waiter.waitForCondition(ExpectedConditions.visibilityOf(surnameField));
        surnameField.sendKeys(surname);
        waiter.waitForCondition(ExpectedConditions.visibilityOf(emailField));
        emailField.sendKeys(email);
        waiter.waitForCondition(ExpectedConditions.visibilityOf(usernameField));
        usernameField.sendKeys(username);
        waiter.waitForCondition(ExpectedConditions.visibilityOf(passwordField));
        passwordField.sendKeys(password);
        waiter.waitForCondition(ExpectedConditions.visibilityOf(roleField));
        roleField.sendKeys(role);
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(createUser));
        createUser.click();
    }

    public boolean isUserPresent(String name){
        By userLocator = By.xpath("//table/tbody[1]/tr/td[3]/span[text()= '" + name + "']");
        return waiter.waitForCondition(ExpectedConditions.visibilityOfElementLocated(userLocator));
    }
}
