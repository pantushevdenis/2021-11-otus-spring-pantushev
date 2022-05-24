package ru.otus.spring.pantushev.dao;

import ru.otus.spring.pantushev.domains.Question;
import ru.otus.spring.pantushev.domains.QuestionHead;
import ru.otus.spring.pantushev.domains.QuestionsList;

import java.util.ArrayList;
import java.util.List;

public class QuestionDaoMock
    implements QuestionDao
{
    @Override
    public QuestionsList getQuestionsList() throws Exception {
        QuestionsList q = new QuestionsList();
        q.add(new Question(new QuestionHead(1, "What is Spring written in?"), 3));
        q.add(new Question(new QuestionHead(2, "Which of following is not Spring module?"), 4));
        q.add(new Question(new QuestionHead(3, "Which exception class is related to all the exceptions that are thrown in spring application?"), 2));

        return q;
    }
}
