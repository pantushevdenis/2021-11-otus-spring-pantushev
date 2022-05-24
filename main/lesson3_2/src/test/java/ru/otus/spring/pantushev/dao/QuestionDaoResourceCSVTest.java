package ru.otus.spring.pantushev.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import ru.otus.spring.pantushev.config.QuestionsDaoConfig;
import ru.otus.spring.pantushev.domains.Answer;
import ru.otus.spring.pantushev.domains.Question;
import ru.otus.spring.pantushev.domains.QuestionHead;
import ru.otus.spring.pantushev.domains.QuestionsList;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("Тест сервиса доступа к данным CSV")
@ContextConfiguration(
        classes = {QuestionDaoResourceCSV.class}
)
public class QuestionDaoResourceCSVTest {
    @MockBean
    QuestionsDaoConfig questionsDaoConfig;

    @Autowired
    QuestionDaoResourceCSV questionDaoResourceCSV;

    @BeforeEach
    void init() {
        QuestionsDaoConfig.ResourceCSVConfig resourceCSVConfig = new QuestionsDaoConfig.ResourceCSVConfig();
        resourceCSVConfig.setFilename("questionsTest.csv");
        when(questionsDaoConfig.getResourcecsv())
                .thenReturn(resourceCSVConfig);
    }

    @Test
    @DisplayName("questionDaoResourceCSV должна корректно инстанцироваться")
    public void shouldCorrectIntantiated() {
        assertNotNull(questionDaoResourceCSV);
    }

    @Test
    @DisplayName("Правильно загружает данныеиз файла в структуру")
    public void shouldGetQuestionList()
        throws Exception
    {
        QuestionsList qlExamle = new QuestionsList();

        Question q1 = new Question(new QuestionHead(1, "Question1"),2);
        q1.getAnswers().addAll(
            Arrays.asList(new Answer("Answer1_1"), new Answer("Answer1_2"), new Answer("Answer1_3")));
        qlExamle.add(q1);

        Question q2 = new Question(new QuestionHead(2, "Question2"),3);
        q2.getAnswers().addAll(
                Arrays.asList(new Answer("Answer2_1"), new Answer("Answer2_2"), new Answer("Answer2_3"), new Answer("Answer2_4")));
        qlExamle.add(q2);

        QuestionsList ql = questionDaoResourceCSV.getQuestionsList();
        assertEquals(qlExamle, ql);
    }
}
