package ru.otus.spring.pantushev.service;

import org.junit.jupiter.api.*;
import ru.otus.spring.pantushev.dao.QuestionDaoMock;
import ru.otus.spring.pantushev.domains.ResultExam;
import ru.otus.spring.pantushev.domains.StudentResponse;
import ru.otus.spring.pantushev.domains.StudentResponsesList;
import ru.otus.spring.pantushev.services.ExamCalcResultServiceImpl;
import ru.otus.spring.pantushev.services.QuestionServiceImpl;

@DisplayName("ExamCalcResultService test")
public class ExamCalcResultServiceImplTest {
    private ExamCalcResultServiceImpl examCalcResultServiceImpl;

    private QuestionServiceImpl questionServiceImpl;

    private QuestionDaoMock questionDaoMock;

    private static StudentResponsesList studentResponseData_passed;
    private static StudentResponsesList studentResponseData_notPassed;



    @BeforeEach
    void PrepareQuestionServiceMock() {
        questionDaoMock = new QuestionDaoMock();
        questionServiceImpl = new QuestionServiceImpl(questionDaoMock);

        examCalcResultServiceImpl = new ExamCalcResultServiceImpl(questionServiceImpl);
        examCalcResultServiceImpl.setPassingPercent(66.6);
    }

    @BeforeAll
    static void PrepareStudentResponseTestData_passedExam() {
        studentResponseData_passed = new StudentResponsesList();
        studentResponseData_passed.add(new StudentResponse(1, 3));
        studentResponseData_passed.add(new StudentResponse(2, 4));
        studentResponseData_passed.add(new StudentResponse(3, 1)); //wrong resp
    }

    @BeforeAll
    static void PrepareStudentResponseTestData_notPassedExam() {
        studentResponseData_notPassed = new StudentResponsesList();
        studentResponseData_notPassed.add(new StudentResponse(1, 3));
        studentResponseData_notPassed.add(new StudentResponse(2, 1)); //wrong resp
        studentResponseData_notPassed.add(new StudentResponse(3, 1)); //wrong resp
    }

    @Test
    @DisplayName("Должно расчитаться экзамен сдан и правильные результаты расчета")
    void TestExamPassed() {
        try {
            ResultExam re =  examCalcResultServiceImpl.CalcResultExam(studentResponseData_passed);
            Assertions.assertEquals(true, re.getExamSuccesful());
            Assertions.assertEquals(3, re.getAnswersTotal());
            Assertions.assertEquals(2, re.getAnswersCorrect());
            Assertions.assertEquals(66.6, re.getPercentageOfCorrectAnswers());

        } catch (Exception e) {
            Assertions.fail("Вызывается исключение!");
        }
    }

    @Test
    @DisplayName("Должно расчитаться экзамен не сдан и правильные результаты расчета")
    void TestExamNotPassed() {
        try {
            ResultExam re =  examCalcResultServiceImpl.CalcResultExam(studentResponseData_notPassed);
            Assertions.assertEquals(false, re.getExamSuccesful());
            Assertions.assertEquals(3, re.getAnswersTotal());
            Assertions.assertEquals(1, re.getAnswersCorrect());
            Assertions.assertEquals(33.3, re.getPercentageOfCorrectAnswers());
        } catch (Exception e) {
            Assertions.fail("Вызывается исключение!");
        }
    }


}

