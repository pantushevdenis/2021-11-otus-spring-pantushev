package ru.otus.spring.pantushev.dao;

import ru.otus.spring.pantushev.beans.QuestionBean;
import ru.otus.spring.pantushev.dao.QuestionDao;

import java.io.IOException;
import java.util.Iterator;

public class QuestionDaoDummy
    implements QuestionDao
{
    @Override
    public QuestionBean getFirstQuestion() {
        return new QuestionBean(1, "How old are your?", "< 16, >= 16 and < 45, > 45, I do not remember");
    }

    @Override
    public Iterator<QuestionBean> getQuestionsAsIterator()
            throws IOException
    {
        return null;
    }
}
