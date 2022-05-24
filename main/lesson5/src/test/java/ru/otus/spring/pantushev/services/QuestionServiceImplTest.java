package ru.otus.spring.pantushev.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring.pantushev.dao.QuestionDao;
import ru.otus.spring.pantushev.dao.mock.QuestionDaoMock;
import ru.otus.spring.pantushev.domains.Answer;
import ru.otus.spring.pantushev.domains.Question;
import ru.otus.spring.pantushev.domains.QuestionHead;
import ru.otus.spring.pantushev.domains.QuestionsList;

import java.util.Arrays;
import java.util.List;

@DisplayName("Тест сервиса вопросов")
@SpringBootTest(classes = {QuestionServiceImpl.class, QuestionDaoMock.class})
public class QuestionServiceImplTest {
    @Autowired
    @Qualifier("questionDaoMock")
    private QuestionDao questionDao;

    @Autowired
    private QuestionService questionService;

    @Test
    @DisplayName("getQuestionsList: Должен возвращать список из трех вопросов")
    void shouldReturnCorrectQuestionList() throws Exception{
        QuestionsList qlSample = new QuestionsList();

        Question q1 = new Question(new QuestionHead(1, "What is Spring written in?"), 3);
        q1.getAnswers().addAll(Arrays.asList(new Answer("Answer1"), new Answer("Answer2"), new Answer("Answer3")));
        qlSample.add(q1);

        qlSample.add(new Question(new QuestionHead(2, "Which of following is not Spring module?"), 4));
        qlSample.add(new Question(new QuestionHead(3, "Which exception class is related to all the exceptions that are thrown in spring application?"), 2));

        List<Question> ql = questionService.getQuestionsList();

        Assertions.assertEquals(qlSample, ql);
    }

    @Test
    @DisplayName(" getQuestionHeadsList: Должен возвращать список из трех заголовков вопросов ")
    void shouldReturnCorrectHeadsList() throws Exception {
        List<QuestionHead> lqh = questionService.getQuestionHeadsList();
        Assertions.assertEquals(
                Arrays.asList(
                        new QuestionHead(1, "What is Spring written in?"),
                        new QuestionHead(2, "Which of following is not Spring module?"),
                        new QuestionHead(3, "Which exception class is related to all the exceptions that are thrown in spring application?")
                )
                , lqh
        );
    }


    @Test
    @DisplayName("getIdCorrectAnswer: Должен возвращать 3 для 1 и 4 для 2")
    void shouldReturnCorrectCorrectAnswer() throws Exception {
        int qa1 = questionService.getIdCorrectAnswer(1);
        Assertions.assertEquals(3, qa1);
        int qa2 = questionService.getIdCorrectAnswer(2);
        Assertions.assertEquals(4, qa2);
    }

    @Test
    @DisplayName("getAnswersList: Должен возвращать для первого вопроса список из трех ответов")
    void shouldReturnCorrectAnswerList() throws Exception {
        List<Answer> la = questionService.getAnswersList(1);
        Assertions.assertEquals(
                Arrays.asList(
                        new Answer("Answer1"),
                        new Answer("Answer2"),
                        new Answer("Answer3")
                ),
                la
        );
    }

    @Test
    @DisplayName("getQuestionsQuantity: Должен возвращать 3 для тестового мока")
    void shouldReturnCorrectQuantity() throws Exception {
        int qq =  questionService.getQuestionsQuantity();
        Assertions.assertEquals(3, qq);
    }

}
