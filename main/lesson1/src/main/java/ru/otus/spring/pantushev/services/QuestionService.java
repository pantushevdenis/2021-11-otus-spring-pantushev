package ru.otus.spring.pantushev.services;

import ru.otus.spring.pantushev.beans.QuestionBean;

import java.io.IOException;
import java.util.Iterator;

public interface QuestionService {
    QuestionBean getFirstQuestion();
    Iterator<QuestionBean> getQuestionsAsIterator() throws IOException;
}
