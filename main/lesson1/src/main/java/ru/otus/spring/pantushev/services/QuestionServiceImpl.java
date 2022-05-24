package ru.otus.spring.pantushev.services;

import ru.otus.spring.pantushev.beans.QuestionBean;
import ru.otus.spring.pantushev.dao.QuestionDao;

import java.io.IOException;
import java.util.Iterator;

public class QuestionServiceImpl
    implements QuestionService
{
    private final QuestionDao dao;

    public QuestionServiceImpl(QuestionDao dao) {
        this.dao = dao;
    }

    @Override
    public QuestionBean getFirstQuestion() {
        return dao.getFirstQuestion();
    }

    @Override
    public Iterator<QuestionBean> getQuestionsAsIterator()
            throws IOException
    {
        return dao.getQuestionsAsIterator();
    }
}
