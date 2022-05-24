package ru.otus.spring.pantushev.dao;

import ru.otus.spring.pantushev.domains.QuestionsList;

import java.io.IOException;

public class QuestionDaoDummy
    implements QuestionDao
{
    @Override
    public QuestionsList getQuestionsList()
            throws Exception
    {
        return null;
    }
}
