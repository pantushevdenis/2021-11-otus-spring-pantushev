package ru.otus.spring.pantushev.dao.mock;

import org.springframework.stereotype.Component;
import ru.otus.spring.pantushev.dao.QuestionDao;
import ru.otus.spring.pantushev.domains.Answer;
import ru.otus.spring.pantushev.domains.Question;
import ru.otus.spring.pantushev.domains.QuestionHead;
import ru.otus.spring.pantushev.domains.QuestionsList;

import java.util.Arrays;
import java.util.List;

@Component
public class QuestionDaoMock
    implements QuestionDao
{
    @Override
    public QuestionsList getQuestionsList() throws Exception {
        QuestionsList q = new QuestionsList();

        Question q1 = new Question(new QuestionHead(1, "What is Spring written in?"), 3);
        q1.getAnswers().addAll(Arrays.asList(new Answer("Answer1"), new Answer("Answer2"), new Answer("Answer3")));
        q.add(q1);

        q.add(new Question(new QuestionHead(2, "Which of following is not Spring module?"), 4));
        q.add(new Question(new QuestionHead(3, "Which exception class is related to all the exceptions that are thrown in spring application?"), 2));

        return q;
    }
}
