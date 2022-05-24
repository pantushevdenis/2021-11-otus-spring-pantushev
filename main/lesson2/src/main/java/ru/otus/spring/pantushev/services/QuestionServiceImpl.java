package ru.otus.spring.pantushev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.spring.pantushev.dao.QuestionDao;
import ru.otus.spring.pantushev.dao.QuestionDaoResourceCSV;
import ru.otus.spring.pantushev.domains.Answer;
import ru.otus.spring.pantushev.domains.Question;
import ru.otus.spring.pantushev.domains.QuestionHead;
import ru.otus.spring.pantushev.domains.QuestionsList;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class QuestionServiceImpl
    implements QuestionService
{
    private final QuestionDao dao;

    private QuestionsList questionsListCach;
    private Map<Integer, Question>  questionListCachIndex;
    private List<QuestionHead> questionHeadListCach;

    @Autowired
    //@Qualifier("questionDaoResourceCSV")
    public QuestionServiceImpl(QuestionDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Question> getQuestionsList()
            throws Exception
    {
        CheckAndCachExc();
        return questionsListCach;
    }

    @Override
    public int getIdCorrectAnswer(int idQuestion) throws Exception {
        CheckAndCachIndexExc();
        Question q = questionListCachIndex.get(idQuestion);
        if (q == null)
        {
            throw new RuntimeException("QuestionServiceImpl.getIdcorrectAnswer: idQuestion not found in questionIndex!");
        }
        return q.getIdCorrectAnswer();
    }

    @Override
    public List<QuestionHead> getQuestionHeadsList() throws Exception {
        CheckAndCachHeadListCachExc();
        return questionHeadListCach;
    }

    @Override
    public List<Answer> getAnswersList(int idQuestion) throws Exception {
        CheckAndCachIndexExc();
        Question q = questionListCachIndex.get(idQuestion);
        if (q == null)
        {
            throw new RuntimeException("QuestionServiceImpl.getIdcorrectAnswer: idQuestion not found in questionIndex!");
        }
        return q.getAnswers();
    }


    private void CheckAndCachExc() throws Exception {
        if (questionsListCach == null)
        {
            questionsListCach = dao.getQuestionsList();
        }
    }

    private void CheckAndCachIndexExc() throws Exception {
        if (questionListCachIndex == null)
        {
            CheckAndCachExc();
            questionListCachIndex = questionsListCach.stream()
                    .collect(Collectors.toMap(Question::getId, Function.identity()));
        }
    }

    private void CheckAndCachHeadListCachExc() throws Exception {
        if (questionHeadListCach == null)
        {
            CheckAndCachExc();
            questionHeadListCach = questionsListCach.stream()
                    .map(i -> i.getHead())
                    .collect(Collectors.toList());
        }
    }
}
