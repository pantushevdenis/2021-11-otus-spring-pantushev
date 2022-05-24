package ru.otus.spring.pantushev.services;

import ru.otus.spring.pantushev.domains.Answer;
import ru.otus.spring.pantushev.domains.Question;
import ru.otus.spring.pantushev.domains.QuestionHead;

import java.util.List;


public interface QuestionService {
    List<Question> getQuestionsList() throws Exception;

    List<QuestionHead> getQuestionHeadsList() throws Exception;
    int getIdCorrectAnswer(int idQuestion) throws Exception;
    List<Answer> getAnswersList(int idQuestion) throws Exception;

    default int getQuestionsQuantity() throws Exception {
        return getQuestionsList().size();
    }

}
