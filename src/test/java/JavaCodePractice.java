import factory.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import pages.*;
import utils.CSVUtils;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class JavaCodePractice {

    private WebDriver driver;
    private SignInPage signInPage;
    private InterviewPage interviewPage;
    private QuestionPage questionPage;
    private QuizPage quizPage;
    private ModulePage modulePage;
    private CoursePage coursePage;
    private UserPage userPage;
    private DevelopmentPage developmentPage;
    private ExamPage examPage;



    private static final String CSV_USER_FILE_PATH = "PairwiseUsers.csv";
    private static final String CSV_INTERVIEW_FILE_PATH = "PairwiseInterviews.csv";


    @BeforeEach
    public void setUp() {
        driver = new WebDriverFactory().create();
        signInPage = new SignInPage(driver);
        interviewPage = new InterviewPage(driver);
        questionPage = new QuestionPage(driver);
        quizPage = new QuizPage(driver);
        modulePage = new ModulePage(driver);
        coursePage = new CoursePage(driver);
        userPage = new UserPage(driver);
        developmentPage = new DevelopmentPage(driver);
        examPage = new ExamPage(driver);
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
        interviewPage.clickInterviewButton();
        interviewPage.createNewInterview(newInterview);
        interviewPage.isInterviewPresent(newInterview);

        assertTrue(interviewPage.isInterviewPresent(newInterview),
                "Интервью с названием '" + newInterview + "' не найдено.");
    }

    @Test
    public void questionTest() {
        String newQuestion = "Хто я?";
        signInPage.open("/");
        signInPage.login();
        questionPage.createNewQuestion(newQuestion);
        questionPage.isQuestionPresent(newQuestion);

        assertTrue(questionPage.isQuestionPresent(newQuestion),
                "Вопрос с названием '" + newQuestion + "' не найден.");
    }

    @Test
    public void quizTest() {
        String newQuiz = "КВИИИЗ";
        signInPage.open("/");
        signInPage.login();
        quizPage.createNewQuiz(newQuiz);
        quizPage.isQuizPresent(newQuiz);

        assertTrue(quizPage.isQuizPresent(newQuiz),
                "Квиз с названием '" + newQuiz + "' не найден.");
    }

    @Test
    public void moduleTest() {
        String newModule = "НОВЫЙ МОДУЛЬ";
        signInPage.open("/");
        signInPage.login();
        modulePage.createNewModule(newModule);
        modulePage.isModulePresent(newModule);

        assertTrue(modulePage.isModulePresent(newModule),
                "Модуль с названием '" + newModule + "' не найден.");
    }
    // 6 тест
    @Test
    public void courseTest() {
        String courseName = "НОВЫЙ КУРС";
        signInPage.open("/");
        signInPage.login();
        String lastModuleId = modulePage.getModuleId();
        coursePage.createNewCourse(lastModuleId, courseName);
        coursePage.isCoursePresent(courseName);

        assertTrue(coursePage.isCoursePresent(courseName),
                "Курс с названием '" + courseName + "' не найден.");
    }


    static Stream<Arguments> csvUserData() {
        List<String[]> data = CSVUtils.loadCSVData(CSV_USER_FILE_PATH);

        return data.stream()
                .filter(values -> values.length >= 6)
                .map(values -> Arguments.of(
                        values[0], values[1], values[2], values[3], values[4], values[5]
                ));
    }
    // 7 тест
    @ParameterizedTest
    @MethodSource("csvUserData")
    public void createUsersTest(String name, String surname, String email, String username, String password, String role){
        signInPage.open("/");
        signInPage.login();
        userPage.createNewUser(name, surname, email, username, password, role);
        userPage.isUserPresent(name);

        assertTrue(userPage.isUserPresent(name),
                "Пользователь с именем '" + name + "' не найден.");
    }
    //8 тест
    @Test
    public void voiceRecordTest() {
        developmentPage.setBaseUrl("voiceTest");
        developmentPage.open("/");
        developmentPage.login();
        developmentPage.voiceRecord();
    }

    static Stream<Arguments> csvInterviewData() {
        List<String[]> data = CSVUtils.loadCSVData(CSV_INTERVIEW_FILE_PATH);
        return data.stream()
                .filter(values -> values.length == 5)
                .map(values -> Arguments.of(values[0], values[1], values[2], values[3], values[4]));
    }
    // 9 тест
    @ParameterizedTest
    @MethodSource("csvInterviewData")
    public void interviewRedactTest(String name, String date, String type, String grade, String link) {
        signInPage.open("/");
        signInPage.login();
        interviewPage.clickInterviewButton();
        interviewPage.interviewRedaction(name, date, type, grade, link);
    }
    // 10 тест
    @Test
    public void examTest(){
        String newExamName = "НОВЫЙ ЭКЗАМЕНУС";
        examPage.open("/");
        examPage.login();
        String lastQuestionId = questionPage.getQuestionId();
        examPage.createNewExam(newExamName, lastQuestionId);
        examPage.isExamPresent(newExamName);

        assertTrue(examPage.isExamPresent(newExamName),
                "Экамен с названием '" + newExamName + "' не найден.");
    }
}
