package ru.otus.spring.pantushev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.pantushev.domains.*;
import ru.otus.spring.pantushev.services.ext.UIStudentResponse;

import java.util.List;

@Service
public class ExamExecutorServiceImpl
        implements ExamExecutorService
{
    private final QuestionService questionsService;
    private final ExamCalcResultServiceImpl examCalcResultServiceImpl;
    private final UIService ioService;
    private final MessageSourceServiceImpl messageSourceServiceImpl;


    @Autowired
    public ExamExecutorServiceImpl(
            QuestionService questionsService,
            ExamCalcResultServiceImpl examCalcResultServiceImpl,
            UIService ioService,
            MessageSourceServiceImpl messageSourceServiceImpl
    ) {
        this.questionsService = questionsService;
        this.examCalcResultServiceImpl = examCalcResultServiceImpl;
        this.ioService = ioService;
        this.messageSourceServiceImpl = messageSourceServiceImpl;
    }

    @Override
    public boolean oneExamExecute() {
        StudentResponsesList studentResponsesList = new StudentResponsesList();
        try {
            List<Question> questionsList = questionsService.getQuestionsList(); //Это не нужно повторять, просто для демонстрации кэширования загрузки экзамена в дао.
            for (Question q : questionsList) {
                Object[] argsRequest =  {q.getId(), q.getBody(), getAnswersAsText(q.getAnswers())};
                Object[] argsAdmonition = {};
                UIStudentResponse uiStudentResponse = ioService.requestAnswer(messageSourceServiceImpl.getMessage("question", argsRequest));
                if (uiStudentResponse.isQuiteFromExam()) {
                    break;
                }
                StudentResponse studentResponse = new StudentResponse(q.getId(), uiStudentResponse.getAnswer());
                studentResponsesList.add(studentResponse);
            }

            ResultExam resultExam = examCalcResultServiceImpl.calcResultExam(studentResponsesList);
            Object[] argsRescalc =  {resultExam.getAnswersCorrect(), resultExam.getAnswersTotal(), resultExam.getPercentageOfCorrectAnswers()};
            String resMessage = messageSourceServiceImpl.getMessage("rescalc", argsRescalc);
            if (resultExam.getExamSuccesful())
            {
                resMessage += messageSourceServiceImpl.getMessage("exampassed", null);
            }
            else {
                resMessage += messageSourceServiceImpl.getMessage("examnotpassed", null);
            }
            ioService.showMessage(resMessage);
        }
        catch (Exception e)
        {
            String[] eMsg = {e.getMessage()};
            ioService.showMessage(messageSourceServiceImpl.getMessage("error", eMsg));
        }
        return true;
    }

    @Override
    public void examExecute() {
        ioService.showMessage(messageSourceServiceImpl.getMessage("greeting", null));

        do {
            oneExamExecute();
        } while(ioService.requestYes(messageSourceServiceImpl.getMessage("repeatexam", null)));
    }

    private String getAnswersAsText(List<Answer> answers)
    {
        String res = "";
        int idAnswer = 1;
        for (Answer a : answers) {
            res += "\t" + idAnswer + ":" + a.getBody() + "\n";
            idAnswer++;
        }
        return res;
    }



}
