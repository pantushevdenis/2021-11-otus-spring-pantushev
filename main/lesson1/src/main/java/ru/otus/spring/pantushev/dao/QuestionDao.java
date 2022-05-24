package ru.otus.spring.pantushev.dao;

import ru.otus.spring.pantushev.beans.QuestionBean;

import java.io.IOException;
import java.util.Iterator;

public interface QuestionDao {
    public QuestionBean getFirstQuestion();
    Iterator<QuestionBean> getQuestionsAsIterator() throws IOException;
}
