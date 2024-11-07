import factory.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class JavaCodePractice {

    private WebDriver driver;
    private SignInPage signInPage;
    private InterviewPage interviewPage;
    private QuestionPage questionPage;
    private QuizPage quizPage;
    private ModulePage modulePage;

    @BeforeEach
    public void setUp() {
        driver = new WebDriverFactory().create();
        signInPage = new SignInPage(driver);
        interviewPage = new InterviewPage(driver);
        questionPage = new QuestionPage(driver);
        quizPage = new QuizPage(driver);
        modulePage = new ModulePage(driver);
    }

    @AfterEach
    public void tearDown() {
        if(driver != null){
            driver.close();
            driver.quit();
        }
    }

    @Test
    public void SignInTest() {
        signInPage.open("/");
        signInPage.login();

        assertTrue(signInPage.isLoginConfirmed(),
                "Пользователь не авторизован");
    }

    @Test
    public void InterviewTest(){
        String newInterview = "Новое интервью";
        signInPage.open("/");
        signInPage.login();
        interviewPage.createNewInterview(newInterview);
        interviewPage.isInterviewPresent(newInterview);

        assertTrue(interviewPage.isInterviewPresent(newInterview),
                "Интервью с названием '" + newInterview + "' не найдено.");
    }

    @Test
    public void QuestionTest() {
        String newQuestion = "Хто я?";
        signInPage.open("/");
        signInPage.login();
        questionPage.createNewQuestion(newQuestion);
        questionPage.isQuestionPresent(newQuestion);

        assertTrue(questionPage.isQuestionPresent(newQuestion),
                "Вопрос с названием '" + newQuestion + "' не найден.");
    }

    @Test
    public void QuizTest() {
        String newQuiz = "КВИИИЗ";
        signInPage.open("/");
        signInPage.login();
        quizPage.createNewQuiz(newQuiz);
        quizPage.isQuizPresent(newQuiz);

        assertTrue(quizPage.isQuizPresent(newQuiz),
                "Квиз с названием '" + newQuiz + "' не найден.");
    }

    @Test
    public void ModuleTest() {
        String newModule = "НОВЫЙ МОДУЛЬ";
        signInPage.open("/");
        signInPage.login();
        modulePage.createNewModule(newModule);
        modulePage.isModulePresent(newModule);

        assertTrue(modulePage.isModulePresent(newModule),
                "Модуль с названием '" + newModule + "' не найден.");
    }
}
