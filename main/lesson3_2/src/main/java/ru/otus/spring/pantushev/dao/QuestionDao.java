package ru.otus.spring.pantushev.dao;

import ru.otus.spring.pantushev.domains.QuestionsList;

public interface QuestionDao {
    QuestionsList getQuestionsList() throws Exception;
}
